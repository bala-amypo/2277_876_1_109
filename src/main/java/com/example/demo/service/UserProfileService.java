package com.example.demo.service;

import java.util.*;
import com.example.demo.entity.UserProfile;

public interface UserProfileService {

    UserProfile createUser(UserProfile u);

    UserProfile getUserById(Long id);

    List<UserProfile> getAllUsers();

    // REQUIRED BY AuthController
    boolean existsByEmail(String email);

    UserProfile getByEmail(String email);
}
