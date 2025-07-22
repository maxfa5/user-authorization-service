package org.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.project.model.User;
import org.project.service.UserService;
import lombok.RequiredArgsConstructor;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class AdminPanelController {
    private final UserService service;

    @GetMapping("/admin/users")
    public List<User> getUsers() {
        return service.getUsers();
    }
}
