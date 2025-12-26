package com.example.demo.security;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserProfileRepository repo;

    public CustomUserDetailsService(UserProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserProfile u = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(u.getEmail())
                .password(u.getPassword())   // PasswordEncoding handled in registration
                .roles(u.getRole())
                .build();
    }
}
