package cpan228.finalprojectdistributioncenter.controller.REST;

import cpan228.finalprojectdistributioncenter.repository.DistributionCenterRepository;
import cpan228.finalprojectdistributioncenter.repository.ItemRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/delete", produces = "application/json")
public class CenterDeleteController {

    private final DistributionCenterRepository centerRepository;

    private final ItemRepository itemRepository;

    public CenterDeleteController(DistributionCenterRepository centerRepository, ItemRepository itemRepository) {
        this.centerRepository = centerRepository;
        this.itemRepository = itemRepository;
    }

    @DeleteMapping("/{id}")
    public void deleteSelectedCenter(@PathVariable("id") Long id) {
        centerRepository.deleteById(id);
    }

    @DeleteMapping("/items/{itemId}")
    public void deleteItemFromSelectedCenter(@PathVariable("itemId") Long itemId) {
        itemRepository.deleteById(itemId);
    }

    @DeleteMapping("/{id}/items/all")
    public void deleteAllItemsFromSelectedCenter(@PathVariable Long id) {
        var selectedCenter = centerRepository.findById(id);
        var selectedCenterItems = selectedCenter.get().getItems();
        selectedCenterItems.clear();
    }

    @DeleteMapping("/all/centers")
    public void deleteAllCenters() {
        centerRepository.deleteAll();
    }

    @DeleteMapping("/all/items")
    public void deleteAllItemsFromCenter() {
        itemRepository.deleteAll();
    }


}

