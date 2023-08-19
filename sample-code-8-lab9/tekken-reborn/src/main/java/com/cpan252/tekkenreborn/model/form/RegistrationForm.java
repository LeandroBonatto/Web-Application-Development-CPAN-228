package com.cpan252.tekkenreborn.model.form;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.cpan252.tekkenreborn.model.User;

import lombok.Data;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private int wins = 0;
    private int losses = 0;
    private int draws = 0;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(this.getUsername())
                .password(passwordEncoder.encode(this.getPassword()))
                .wins(this.getWins())
                .losses(this.getLosses())
                .draws(this.getDraws())
                .build();
    }

}

