package org.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/api/user")
public class UserCheckController {
    @GetMapping("/check-user")
    public String checkUser(HttpServletRequest request) {
        return "User is authenticated";
    }
    @GetMapping("/check-admin")
    public String checkAdmin(HttpServletRequest request) {
        return "Admin is authenticated";
    }
    @GetMapping("/check-premium")
    public String checkPremium(HttpServletRequest request) {
        return "Premium user is authenticated";
    }
    @GetMapping("/check-user-role")
    public String checkUserRole(HttpServletRequest request) {
        return "User role is " + request.getAttribute("role");
    }
}
