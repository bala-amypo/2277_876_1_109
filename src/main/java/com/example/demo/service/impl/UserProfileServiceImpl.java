package com.example.demo.service.impl;

import java.util.*;
import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repo;
    private final PasswordEncoder encoder;

    public UserProfileServiceImpl(UserProfileRepository r, PasswordEncoder e) {
        this.repo = r;
        this.encoder = e;
    }

    public UserProfile createUser(UserProfile u) {
        u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }

    public UserProfile getUserById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User"));
    }

    public List<UserProfile> getAllUsers() {
        return repo.findAll();
    }
}
