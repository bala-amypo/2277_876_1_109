package com.example.demo.controller;

import com.example.demo.entity.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserProfileController {

    private final UserProfileService userService;

    public UserProfileController(UserProfileService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserProfile createUser(@RequestBody UserProfile user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public UserProfile getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserProfile> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}/status")
    public UserProfile updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return userService.updateUserStatus(id, active);
    }

    @GetMapping("/by-userid/{userId}")
    public UserProfile findByUserId(@PathVariable String userId) {
        return userService.findByUserId(userId);
    }
}
