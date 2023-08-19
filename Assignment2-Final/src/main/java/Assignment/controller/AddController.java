package Assignment.controller;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Assignment.model.Item;
import Assignment.model.Item.Brand;
import Assignment.repository.ItemRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/add")
public class AddController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public String add() {
        return "add";
    }

    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Brand.class);
        model.addAttribute("brands", brands);
        log.info("items converted to string:  {}", brands);
    }

    @ModelAttribute
    public Item item() {
        return Item
                .builder()
                .build();
    }

    @PostMapping
    public String processItem(@Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        log.info("Processing item:" + item);
        itemRepository.save(item);
        return "redirect:/items";
    }
}
