package com.example.demo.security;

import com.example.demo.entity.UserProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserProfileRepository userProfileRepository;

    public CustomerUserDetailsService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        UserProfile user = userProfileRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with email: " + email)
                );

        if (!user.isActive()) {
            throw new UsernameNotFoundException("User account is inactive");
        }

        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}
