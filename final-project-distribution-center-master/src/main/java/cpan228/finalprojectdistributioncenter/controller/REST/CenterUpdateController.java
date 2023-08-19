package cpan228.finalprojectdistributioncenter.controller.REST;


import cpan228.finalprojectdistributioncenter.model.DistributionCenter;
import cpan228.finalprojectdistributioncenter.model.Item;
import cpan228.finalprojectdistributioncenter.model.dto.CreateCenter;
import cpan228.finalprojectdistributioncenter.model.dto.UpdateItem;
import cpan228.finalprojectdistributioncenter.repository.DistributionCenterRepository;
import cpan228.finalprojectdistributioncenter.repository.DistributionCenterRepositoryPaginated;
import cpan228.finalprojectdistributioncenter.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "/api/update", produces = "application/json")
public class CenterUpdateController {

    private final DistributionCenterRepositoryPaginated centerRepositoryPaginated;
    private final DistributionCenterRepository centerRepository;

    private final ItemRepository itemRepository;

    public CenterUpdateController(DistributionCenterRepositoryPaginated centerRepositoryPaginated,
                                DistributionCenterRepository centerRepository, ItemRepository itemRepository) {
        this.centerRepositoryPaginated = centerRepositoryPaginated;
        this.centerRepository = centerRepository;
        this.itemRepository = itemRepository;
    }

    @PutMapping("/{id}")
    public DistributionCenter updateCenter(@PathVariable("id") Long id,
                                           @Valid @RequestBody CreateCenter center) {
        var centerToUpdate = centerRepository.findById(id).orElseThrow();
        centerToUpdate.setName(center.getName());
        centerToUpdate.setItems(center.getItems());
        centerToUpdate.setLatitude(center.getLatitude());
        centerToUpdate.setLongitude(center.getLongitude());
        return centerRepository.save(centerToUpdate);
    }

    @PutMapping("/items/{itemId}")
    public Item updateCenterItem(@PathVariable Long itemId, @Valid @RequestBody UpdateItem item) {
        var itemToUpdate = itemRepository.findById(itemId).orElseThrow();
        itemToUpdate.setName(item.getName());
        itemToUpdate.setQuantity(item.getQuantity());
        itemToUpdate.setPrice(item.getPrice());
        itemToUpdate.setBrandFrom(item.getBrandFrom());
        itemToUpdate.setCreateYear(item.getCreateYear());
        itemToUpdate.setDistributionCenter(itemToUpdate.getDistributionCenter());
        return itemRepository.save(itemToUpdate);
    }
}
