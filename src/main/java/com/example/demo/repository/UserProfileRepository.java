package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.*;

public interface UserProfileRepository {
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);
    Optional<UserProfile> findById(Long id);
    Optional<UserProfile> findByEmail(String email);
    List<UserProfile> findAll();
    UserProfile save(UserProfile u);
}
