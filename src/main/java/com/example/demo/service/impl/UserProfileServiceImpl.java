package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserProfile register(UserProfile userProfile) {

        if (repository.existsByUserId(userProfile.getUserId())) {
            throw new RuntimeException("UserId already exists");
        }

        if (repository.existsByEmail(userProfile.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return repository.save(userProfile);
    }

    @Override
    public UserProfile login(String email, String password) {

        UserProfile user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!Boolean.TRUE.equals(user.getActive())) {
            throw new RuntimeException("User is inactive");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
}
