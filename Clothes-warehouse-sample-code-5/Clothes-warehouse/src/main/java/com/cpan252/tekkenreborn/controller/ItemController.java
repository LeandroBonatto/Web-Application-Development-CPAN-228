package com.cpan252.tekkenreborn.controller;

import ch.qos.logback.core.model.Model;
import com.cpan252.tekkenreborn.model.Item;
import com.cpan252.tekkenreborn.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * How applicatiion context and dependency injection works in Spring Boot
 * Bean in Spring Boot is a Java object that is managed by Spring
 * How Spring manages beans:
 * 1. Spring Boot creates an Application Context
 * 2. Spring Boot scans the classpath for beans (annotated with @Component or
 * other Component annotations)
 * 3. Spring Boot creates beans and registers them in the Application Context
 * 
 * Controller annotation is just a specialization of @Component annotation
 * Controller serves as a dispatcher for HTTP requests
 */
@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/add")
    public String showAddItemForm(Model model) {
        model.addText("item");
        return "add-item";
    }

    @PostMapping("")
    public String addItem(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        return "redirect:/items/list";
    }

    @GetMapping("/list")
    public String showItemList(Model model) {
        model.addText("items");
        return "list-items";
    }

    @GetMapping("/sort")
    public String sortItemsByBrand(Model model) {
        model.addText("items");
        return "list-items";
    }
}
