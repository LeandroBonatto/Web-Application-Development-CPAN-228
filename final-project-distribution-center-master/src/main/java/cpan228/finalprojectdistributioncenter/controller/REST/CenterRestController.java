package cpan228.finalprojectdistributioncenter.controller.REST;

import java.util.List;
import java.util.Optional;

import cpan228.finalprojectdistributioncenter.model.DistributionCenter;
import cpan228.finalprojectdistributioncenter.model.Item;
import cpan228.finalprojectdistributioncenter.repository.DistributionCenterRepository;
import cpan228.finalprojectdistributioncenter.repository.DistributionCenterRepositoryPaginated;
import cpan228.finalprojectdistributioncenter.repository.ItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/center", produces = "application/json")
public class CenterRestController {

    private final DistributionCenterRepositoryPaginated centerRepositoryPaginated;
    private final DistributionCenterRepository centerRepository;

    private final ItemRepository itemRepository;

    public CenterRestController(DistributionCenterRepositoryPaginated centerRepositoryPaginated,
                                DistributionCenterRepository centerRepository, ItemRepository itemRepository) {
        this.centerRepositoryPaginated = centerRepositoryPaginated;
        this.centerRepository = centerRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public Iterable<DistributionCenter> allCenters(@RequestParam("page") Optional<Integer> page,
                                                   @RequestParam("size") Optional<Integer> size) {
        if (!page.isPresent() || !size.isPresent()) {
            return centerRepository.findAll();
        } else {
            return centerRepositoryPaginated.findAll(PageRequest.of(page.get(), size.get()));
        }
    }

    @GetMapping("/{id}")
    public DistributionCenter getCenter(@PathVariable Long id) {
        var selectedCenter = centerRepository.findById(id);
        return selectedCenter.get();
    }

    @GetMapping("/{id}/items")
    public List<Item> getCenterItems(@PathVariable Long id) {
        var selectedCenter = centerRepository.findById(id);
        var items = selectedCenter.get().getItems();
        return items;
    }

}
