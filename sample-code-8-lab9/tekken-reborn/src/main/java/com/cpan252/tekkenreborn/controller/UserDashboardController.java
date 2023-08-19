package com.cpan252.tekkenreborn.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.cpan252.tekkenreborn.model.dto.TekkenUserDto;
import lombok.extern.slf4j.Slf4j;

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

    @ModelAttribute("users")
    public List<TekkenUserDto> getUsers() {
        var users = restTemplate.getForObject("http://localhost:8082/api/userdashboard", TekkenUserDto[].class);
        log.info("Users: {}", users);
        return Arrays.asList(users);
    }

}

