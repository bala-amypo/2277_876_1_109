package com.example.demo.service;

import com.example.demo.entity.UserProfile;

public interface UserProfileService {

    UserProfile register(UserProfile userProfile);

    UserProfile login(String email, String password);
}
