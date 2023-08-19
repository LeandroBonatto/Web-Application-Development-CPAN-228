package com.cpan252.tekkenreborn.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import com.cpan252.tekkenreborn.model.dto.itemSearchByDateDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cpan252.tekkenreborn.repository.ItemRepository;
import com.cpan252.tekkenreborn.repository.ItemRepositoryPaginated;

@Controller
@RequestMapping("/itemlist")
public class ItemListController {
    private static final int PAGE_SIZE = 5;
    private ItemRepository itemRepository;

    private ItemRepositoryPaginated itemRepositoryPaginated;

    public ItemListController(ItemRepository itemRepository,
            ItemRepositoryPaginated itemRepositoryPaginated) {
        this.itemRepository = itemRepository;
        this.itemRepositoryPaginated = itemRepositoryPaginated;
    }

    @GetMapping
    public String showList(Model model) {
        return "itemlist";
    }

    /**
     * This method will allow us to populate the model with initial fighter details
     * 1. We will use the fighterRepositoryPaginated to retrieve the first page of
     * fighters (we set the page size to 5)
     * 
     * @param model
     */
    @ModelAttribute
    public void item(Model model) {
        var itemPage = itemRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));
        model.addAttribute("item", itemRepository);
        model.addAttribute("currentPage", itemPage.getNumber());
        model.addAttribute("totalPages", itemPage.getTotalPages());
    }

    @ModelAttribute
    public void itemSearchByDateDto(Model model) {
        model.addAttribute("itemByDateDto", new itemSearchByDateDto());
    }

    @PostMapping
    public String searchItemByDate(@ModelAttribute itemSearchByDateDto itemSearchByDateDto,
            Model model) {
        var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        model.addAttribute("item", itemRepository.findByBrandAndYear(
                itemSearchByDateDto.getName(), LocalDate.parse(itemSearchByDateDto.getStartDate(), dateFormatter),
                LocalDate.parse(itemSearchByDateDto.getEndDate(), dateFormatter)));
        return "itemlist";
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model,
            @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch) {
        var page = pageToSwitch.orElse(0);
        var totalPages = (int) model.getAttribute("totalPages");
        if (page < 0 || page >= totalPages) {
            return "itemlist";
        }
        var itemPage = itemRepositoryPaginated.findAll(PageRequest.of(pageToSwitch.orElse(0),
                PAGE_SIZE));
        model.addAttribute("item", itemPage.getContent());
        model.addAttribute("currentPage", itemPage.getNumber());
        return "itemlist";
    }
}
