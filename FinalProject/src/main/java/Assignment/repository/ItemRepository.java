package Assignment.repository;

import java.util.List;

import Assignment.model.DistributionCentre;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import Assignment.model.Item;
import Assignment.model.Item.Brand;

public interface ItemRepository extends CrudRepository<Item, Long>{
    List<Item> findByCreateYearAndBrandFromEquals(int createYear, Brand brandFrom);
    List<Item> findByBrandAndName(String brand, String name);

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public default void requestItem(String itemName, String brand, int quantity) throws Exception {
        DistributionCentre centre = findClosestCentre();

        // fetch the item from the distribution centre
        Item centreItem = itemRepository.findByNameAndBrandAndDistributionCentre(itemName, brand, centre);

        if (centreItem == null || centreItem.getQuantity() < quantity) {
            throw new Exception("Not enough stock in the distribution centre");
        }

        // update the item quantity in the distribution centre
        centreItem.setQuantity(centreItem.getQuantity() - quantity);
        itemRepository.save(centreItem);

        // update the item quantity in the warehouse
        Item warehouseItem = itemRepository.findByNameAndBrand(itemName, brand);
        if (warehouseItem == null) {
            warehouseItem = new Item(itemName, brand, quantity);
        } else {
            warehouseItem.setQuantity(warehouseItem.getQuantity() + quantity);
        }
        itemRepository.save(warehouseItem);
    }

    Item findByNameAndBrand(String itemName, String brand);

    Item findByNameAndBrandAndDistributionCentre(String itemName, String brand, DistributionCentre centre);

    DistributionCentre findClosestCentre();

}
