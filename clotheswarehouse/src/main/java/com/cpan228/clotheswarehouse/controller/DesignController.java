package com.cpan228.clotheswarehouse.controller;

import java.util.EnumSet;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cpan228.clotheswarehouse.model.Cloth;
import com.cpan228.clotheswarehouse.model.ClothPool;
import com.cpan228.clotheswarehouse.model.Cloth.Brand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequestMapping("/design")

@SessionAttributes("clothPool")
public class DesignController {

@GetMapping
public String design() {
return "design";
}

@ModelAttribute
public void brands(Model model) {
var brands = EnumSet.allOf(Brand.class);
model.addAttribute("brands", brands);
//log.info("brands converted to string:{}", brands);
}

        @ModelAttribute(name = "clothPool")
public ClothPool clothPool() {
return new ClothPool();
}

        @ModelAttribute

        public Cloth cloth() {
return Cloth
.builder()
.build();
}

@PostMapping
public String processClothAddition(@Valid Cloth cloth, Errors errors,
@ModelAttribute ClothPool pool) {
//        System.out.println("===== HERE!");
//        System.out.println(cloth);
//        System.out.println(errors);
if (errors.hasErrors()) {
return "design";
}
pool.add(cloth);
return "redirect:/design";
}

}
