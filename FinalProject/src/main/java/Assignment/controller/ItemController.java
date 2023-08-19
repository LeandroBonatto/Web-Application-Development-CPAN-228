package Assignment.controller;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import Assignment.model.Item;
import Assignment.model.Item.Brand;
import Assignment.model.dto.ItemSearchRequest;
import Assignment.model.dto.ItemsSearch;
import Assignment.repository.ItemRepository;
import Assignment.repository.ItemRepositoryPaginated;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/items")
public class ItemController {

    // define the items show in one page
    private static final int PAGE_SIZE = 3;

    private ItemRepository itemRepository;

    private ItemRepositoryPaginated itemRepositoryPaginated;

    public ItemController(ItemRepository itemRepository, ItemRepositoryPaginated itemRepositoryPaginated) {
        this.itemRepository = itemRepository;
        this.itemRepositoryPaginated = itemRepositoryPaginated;
    }

    @GetMapping
    public String showItems(Model model) {
        return "items";
    }

    @ModelAttribute
    public void items(Model model) {
        var itemPage = itemRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));
        model.addAttribute("items", itemPage);
        model.addAttribute("currentPage", itemPage.getNumber());
        model.addAttribute("totalPages", itemPage.getTotalPages());
    }

    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Brand.class);
        model.addAttribute("brands", brands);
    }

    @ModelAttribute
    public void itemsSearch(Model model) {
        model.addAttribute("itemsSearch", new ItemsSearch());
    }

    @PostMapping
    public String searchItems(@ModelAttribute ItemsSearch itemsSearch, Model model) {
        model.addAttribute("items", itemRepository.findByCreateYearAndBrandFromEquals(
                itemsSearch.getCreateYear(),
                itemsSearch.getBrandFrom()));
        return "items";
    }

    @PostMapping("/items/search")
    public List<Item> findItemByBrandAndName(@ModelAttribute ItemSearchRequest ItemSearchRequest ) {
        return itemRepository.findByBrandAndName(ItemSearchRequest.getBrand(), ItemSearchRequest.getName());
    }

    @GetMapping("/switchPage")
    public String switchPage(@RequestParam("page") Optional<Integer> page, Model model) {
        var pageToGoTo = page.orElse(0);
        var totalPages = (int)model.getAttribute("totalPages");
        if (pageToGoTo < 0 || pageToGoTo >= totalPages) {
            return "redirect:/items";
        }
        var itemPage = itemRepositoryPaginated.findAll(PageRequest.of(page.orElse(0), PAGE_SIZE));
        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", itemPage.getNumber());
        return "items";
    }
    @GetMapping("/deleteClothes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteClothes(@PathVariable("id") Long id) {
        itemRepository.deleteById(id);
        return "redirect:/clothesManagement";
    }
}
