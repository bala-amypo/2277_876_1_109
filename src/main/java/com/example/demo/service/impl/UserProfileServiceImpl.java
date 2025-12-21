package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserProfile save(UserProfile user) {
        return repository.save(user);
    }

    @Override
    public Optional<UserProfile> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void updateUserStatus(Long id, boolean active) {
        Optional<UserProfile> userOpt = repository.findById(id);
        if (userOpt.isPresent()) {
            UserProfile user = userOpt.get();
            user.setActive(active);
            repository.save(user);
        }
    }
}
