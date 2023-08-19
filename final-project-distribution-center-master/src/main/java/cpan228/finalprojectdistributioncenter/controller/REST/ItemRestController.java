package cpan228.finalprojectdistributioncenter.controller.REST;

import cpan228.finalprojectdistributioncenter.model.Item;
import cpan228.finalprojectdistributioncenter.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/item", produces = "application/json")
public class ItemRestController {
    private final ItemRepository itemRepository;

    public ItemRestController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public Iterable<Item> allItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/search")
    public Iterable<Item> searchByBrandAndName(@RequestParam("brand") String brand,
                                               @RequestParam("name") String name) {
        return itemRepository.findByNameEqualsAndAndBrandFromEquals(name, Item.Brand.valueOf(brand));
    }

    @GetMapping("/search/brand={brand}")
    public List<Item> searchByBrand(@PathVariable String brand) {
        return itemRepository.findByBrandFromEquals(Item.Brand.valueOf(brand));
    }

    @GetMapping("/search/name={name}")
    public List<Item> searchByName(@PathVariable String name) {
        return itemRepository.findByNameEquals(name);
    }
}

