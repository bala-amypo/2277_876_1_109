package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserProfile createUser(UserProfile userProfile) {
        return repository.save(userProfile);
    }

    @Override
    public UserProfile getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public UserProfile updateUserStatus(Long id, boolean active) {
        UserProfile user = getUserById(id);
        user.setActive(active);
        return repository.save(user);
    }

    @Override
    public UserProfile findByUserId(String userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserProfile login(String email, String password) {
        UserProfile user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!Boolean.TRUE.equals(user.getActive())) {
            throw new RuntimeException("User is inactive");
        }

        return user;
    }
}
