package com.example.demo.service;

import com.example.demo.entity.UserProfile;
import java.util.List;

public interface UserProfileService {

    UserProfile createUser(UserProfile user);       // used in UserProfileController
    UserProfile getUserById(Long id);               // used in UserProfileController
    List<UserProfile> getAllUsers();                // used in UserProfileController
    void updateUserStatus(Long userId, boolean active); // previously missing
    UserProfile findByUserId(String userId);       // used in UserProfileController
}
