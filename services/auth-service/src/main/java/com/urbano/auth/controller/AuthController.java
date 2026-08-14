package com.urbano.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public String register() {
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login() {
        return "User logged in successfully!";
    }
}