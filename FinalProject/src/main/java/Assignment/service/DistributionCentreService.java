package Assignment.service;

import Assignment.controller.ItemController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import Assignment.model.DistributionCentre;
import Assignment.model.Item;
import Assignment.repository.DistributionCentreRepository;
import Assignment.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class DistributionCentreService {

    // warehouse coordinates
    private static final double WAREHOUSE_LATITUDE = 32.50;
    private static final double WAREHOUSE_LONGITUDE = -95.70;

    public double calculateDistance(double centreLat, double centreLon) {
        final int R = 6371; // Radius of the earth in kilometers

        double latDistance = Math.toRadians(centreLat - WAREHOUSE_LATITUDE);
        double lonDistance = Math.toRadians(centreLon - WAREHOUSE_LONGITUDE);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(WAREHOUSE_LATITUDE)) * Math.cos(Math.toRadians(centreLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // returns the distance in kilometers
    }


    private final DistributionCentreRepository distributionCentreRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public DistributionCentreService(DistributionCentreRepository distributionCentreRepository, ItemRepository itemRepository) {
        this.distributionCentreRepository = distributionCentreRepository;
        this.itemRepository = itemRepository;
    }

    @PostMapping("/{centreId}/item")
    public ResponseEntity<Item> addItem(@PathVariable Long centreId, @RequestBody Item item) {
        Item savedItem = distributionCentreService.addItem(centreId, item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        distributionCentreService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<DistributionCentre>> listAllDistributionCentres() {
        List<DistributionCentre> distributionCentres = distributionCentreService.listAllDistributionCentres();
        return new ResponseEntity<>(distributionCentres, HttpStatus.OK);
    }

    public void reduceItemStock(DistributionCentre centre, String name, String brand, int quantity) {
        // Logic to reduce the item stock in the given distribution centre by the given quantity.
        // This will involve querying for the item and updating its quantity.
        Optional<Item> itemOpt = itemRepository.findByNameAndBrandAndDistributionCentre(name, brand, centre);
        if(itemOpt.isPresent()) {
            Item item = itemOpt.get();
            if(item.getQuantity() >= quantity) {
                item.setQuantity(item.getQuantity() - quantity);
                itemRepository.save(item);
            } else {
                // Handle scenario where there isn't enough stock in the centre.
                // You could throw a custom exception here to handle it in the controller.
                throw new InsufficientStockException("Not enough stock available in the centre");
            }
        } else {
            // Handle scenario where item is not found in the given distribution centre.
            // You could throw another custom exception here.
            throw new ItemNotFoundException("Item not found in the centre");
        }
    }

    public void increaseWarehouseStock(String name, String brand, int quantity) {
        Optional<Item> itemOpt = ItemRepository.findByNameAndBrand(name, brand);
        if(itemOpt.isPresent()) {
            Item item = itemOpt.get();
            item.setQuantity(item.getQuantity() + quantity);
            ItemRepository.save(item);
        } else {
            // Handle scenario where item is not found in the warehouse.
            // Perhaps you want to add the item to the warehouse if it doesn't exist?
            Item newItem = new Item();
            newItem.setName(name);
            newItem.setBrand(brand);
            newItem.setQuantity(quantity);
            // Set other attributes of item as necessary.
            ItemRepository.save(newItem);
        }
    }

    @GetMapping("/item")
    public ResponseEntity<Item> requestItemByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        ItemController distributionCentreService;
        Item item = distributionCentreService.findItemByBrandAndName(brand, name);
        if(item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
