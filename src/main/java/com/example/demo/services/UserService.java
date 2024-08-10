package com.example.demo.services;

import com.example.demo.entity.User;

import java.util.Optional;

public interface UserService {
    void registerUser(User user);
    Optional<User> findUserByUsername(String username);
}
