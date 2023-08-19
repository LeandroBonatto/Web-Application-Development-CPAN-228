package cpan228.finalprojectdistributioncenter.controller.REST;

import cpan228.finalprojectdistributioncenter.model.DistributionCenter;
import cpan228.finalprojectdistributioncenter.model.Item;
import cpan228.finalprojectdistributioncenter.model.dto.CreateCenter;
import cpan228.finalprojectdistributioncenter.repository.DistributionCenterRepository;
import cpan228.finalprojectdistributioncenter.repository.DistributionCenterRepositoryPaginated;
import cpan228.finalprojectdistributioncenter.repository.ItemRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "/api/add", produces = "application/json")
public class CenterAddController {

    private final DistributionCenterRepositoryPaginated centerRepositoryPaginated;
    private final DistributionCenterRepository centerRepository;

    private final ItemRepository itemRepository;

    public CenterAddController(DistributionCenterRepositoryPaginated centerRepositoryPaginated,
                                  DistributionCenterRepository centerRepository, ItemRepository itemRepository) {
        this.centerRepositoryPaginated = centerRepositoryPaginated;
        this.centerRepository = centerRepository;
        this.itemRepository = itemRepository;
    }

    @PostMapping
    public DistributionCenter createCenter(@Valid @RequestBody CreateCenter center) {
        return centerRepository.save(center.toDistributionCenter());
    }

    @PostMapping("/{id}/items")
    public Item addItemToCenter(@PathVariable Long id, @Valid @RequestBody Item item) {
        var selectedCenter = centerRepository.findById(id);
        item.setDistributionCenter(selectedCenter.get());
        var savedItem = itemRepository.save(item);
        return savedItem;
    }

}

