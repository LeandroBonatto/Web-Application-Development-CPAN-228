package com.cpan252.clotheswarehouse.controller;

import com.cpan252.clotheswarehouse.model.Item;
import com.cpan252.clotheswarehouse.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpan252.clotheswarehouse.repository.ItemRepository;

@Controller
@RequestMapping("/itemlist")
public class ItemListController {
    private ItemRepository itemRepository;

    public ItemListController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String showItem(Model model) {
        return "itemlist";
    }

    @ModelAttribute
    public void item(Model model) {
        model.addAttribute("item", itemRepository.findAll());
    }
}
