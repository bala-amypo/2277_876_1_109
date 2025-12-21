package com.example.demo.repository;

import com.example.demo.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUserId(String userId);       // used in service/controller
    Optional<UserProfile> findByEmail(String email);         // used in CustomerUserDetailsService
    boolean existsByEmail(String email);                     // used in AuthController
}
