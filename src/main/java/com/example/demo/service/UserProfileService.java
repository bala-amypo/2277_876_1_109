package com.example.demo.service;

import com.example.demo.entity.UserProfile;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserProfileService extends UserDetailsService {

    boolean existsByEmail(String email);

    UserProfile getByEmail(String email);
}
