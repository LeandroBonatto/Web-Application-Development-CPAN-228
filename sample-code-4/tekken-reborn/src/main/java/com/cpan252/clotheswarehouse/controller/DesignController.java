package com.cpan252.clotheswarehouse.controller;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpan252.clotheswarehouse.model.Item;
import com.cpan252.clotheswarehouse.model.Item.fashionableBrands;
import com.cpan252.clotheswarehouse.repository.ItemRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignController {

    /**
     * In java you should use the interface instead of the concrete class.
     * It helps you to switch implementations without having to change the code.
     */
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public String design() {
        return "design";
    }

    @ModelAttribute
    public void fashionableBrands(Model model) {
        var fashionableBrands = EnumSet.allOf(fashionableBrands.class);
        model.addAttribute("fashionableBrands", fashionableBrands);
        log.info("animes converted to string:  {}", fashionableBrands);
    }

    /**
     * 1. We have created a new Fighter object here, to be populated from the form
     * inputs
     * 2. We have to reference the Fighter object properties in the form and bind
     * them to the corresponding inputs
     * 3. We have to submit Form (execute POST request) and make sure fighter
     * details are valid
     * 
     * @return Fighter model that we will need only for request (form) submission
     */
    @ModelAttribute
    // This model attribute has a lifetime of a request
    public Item item() {
        return Item
                .builder()
                .build();
    }

    @PostMapping
    public String processFighterAddition(@Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "design";
        }
        log.info("Processing item: {}");
        itemRepository.save(item);
        return "redirect:/itemlist";
    }
}
