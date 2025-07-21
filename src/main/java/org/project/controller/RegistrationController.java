package org.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.project.model.User;

@RestController
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/register")
    public String register(@RequestBody User user) {
        if (userService.register(user)) {
            return "Registration successful";
        } else {
            return "Registration failed";
        }
    }

    
    

}
