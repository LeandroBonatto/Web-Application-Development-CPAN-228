package com.cpan252.tekkenreborn.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;


import com.cpan252.tekkenreborn.model.dto.TekkenUserDto;

@Controller
@Slf4j
@RequestMapping("/userdashboard")
@CrossOrigin(origins = "http://localhost:8082")
public class UserDashboardController {
    private RestTemplate restTemplate;

    public UserDashboardController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String dashboard() {
        return "userdashboard";
    }

    @ModelAttribute
    public void getUsers(Model model) {
        var users = Arrays.asList(restTemplate.getForObject("http://localhost:8082/api/userdashboard", TekkenUserDto[].class));
        var mostWinUser = users.stream().max(Comparator.comparingInt(TekkenUserDto::getWins)).orElse(null);
        model.addAttribute("users",  users);
        model.addAttribute("mostWinUser", mostWinUser);
    }
}
