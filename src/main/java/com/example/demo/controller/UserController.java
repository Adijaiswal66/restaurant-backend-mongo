package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userCreateService;

    @PostMapping("register")
    public String registerUser(@RequestBody User user) {
        try {
            userCreateService.registerUser(user);
            return "User registered successfully";
        } catch (RuntimeException e) {
            return e.getMessage(); // Handle exceptions appropriately
        }
    }
}
