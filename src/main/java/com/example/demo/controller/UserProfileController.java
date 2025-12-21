package com.example.demo.controller;

import com.example.demo.entity.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    @Autowired
    private UserProfileService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserProfile user) {
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserById(@PathVariable Long id) {
        UserProfile user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        List<UserProfile> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long id, @RequestParam boolean active) {
        userService.updateUserStatus(id, active);
        return ResponseEntity.ok("User status updated successfully");
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<UserProfile> findByUserId(@PathVariable String userId) {
        UserProfile user = userService.findByUserId(userId);
        return ResponseEntity.ok(user);
    }
}
