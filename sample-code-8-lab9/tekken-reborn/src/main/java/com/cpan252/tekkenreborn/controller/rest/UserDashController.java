package com.cpan252.tekkenreborn.controller.rest;

import java.util.Optional;

import com.cpan252.tekkenreborn.model.form.RegistrationForm;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpan252.tekkenreborn.model.User;
import com.cpan252.tekkenreborn.repository.UserRepository;
import com.cpan252.tekkenreborn.repository.UserRepositoryPaginated;

import jakarta.validation.Valid;
@RestController
@RequestMapping(path = "/api/userdashboard", produces = "application/json")
public class UserDashController {
    private final UserRepository userRepository;

    private final UserRepositoryPaginated userRepositoryPaginated;

    private PasswordEncoder passwordEncoder;

    public UserDashController(UserRepository userRepository, UserRepositoryPaginated userRepositoryPaginated, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRepositoryPaginated = userRepositoryPaginated;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public Iterable<User> allUsers(@RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size) {
        if (!page.isPresent() || !size.isPresent()) {
            return userRepository.findAll();
        } else {http://localhost:8082/api/userdashboard
        return userRepositoryPaginated.findAll(PageRequest.of(page.get(), size.get()));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody RegistrationForm user) {
        return userRepository.save(user.toUser(passwordEncoder));
    }

    @PutMapping("/{id}")
    public User updateUserMatchInfo(@PathVariable("id") Long id, @Valid @RequestBody RegistrationForm user) {
        var updatedUser = userRepository.findById(id).orElseThrow();
        updatedUser.setWins(user.getWins());
        updatedUser.setLosses(user.getLosses());
        updatedUser.setDraws(user.getDraws());
        return userRepository.save(updatedUser);
    }
}
