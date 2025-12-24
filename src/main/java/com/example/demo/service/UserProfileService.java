package com.example.demo.service;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile createUser(UserProfile userProfile) {

        if (userProfileRepository.existsByUserId(userProfile.getUserId())) {
            throw new RuntimeException("UserId already exists");
        }

        if (userProfileRepository.existsByEmail(userProfile.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return userProfileRepository.save(userProfile);
    }

    public UserProfile login(String email, String password) {

        UserProfile user = userProfileRepository
                .findByEmail(email)
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
