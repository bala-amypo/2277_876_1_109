package com.example.demo.service;

import com.example.demo.entity.UserProfile;

import java.util.List;

public interface UserProfileService {

    UserProfile createUser(UserProfile userProfile);

    UserProfile getUserById(Long id);

    List<UserProfile> getAllUsers();

    UserProfile updateUserStatus(Long id, boolean active);

    UserProfile findByUserId(String userId);

    UserProfile login(String email, String password);
}
