package com.dining.diningreview.controller;

import com.dining.diningreview.repository.UserRepository;

public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
}
