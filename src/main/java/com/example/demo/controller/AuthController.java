package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserProfileService userService;
    private final UserProfileRepository userProfileRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserProfileService userService,
                          UserProfileRepository userProfileRepository,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.userProfileRepository = userProfileRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest req) {

        if (userProfileRepository.existsByEmail(req.getEmail())) {
            return ResponseEntity.badRequest().body(null);
        }

        UserProfile user = new UserProfile();
        user.setUserId(UUID.randomUUID().toString());
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole() != null ? req.getRole() : "USER");
        user.setActive(true);

        UserProfile saved = userProfileRepository.save(user);

        JwtResponse response = new JwtResponse();
        response.setUserId(saved.getId());
        response.setEmail(saved.getEmail());
        response.setToken(jwtUtil.generateToken(saved.getId(), saved.getEmail(), saved.getRole()));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        UserProfile user = userProfileRepository.findByEmail(req.getEmail()).orElseThrow();

        JwtResponse response = new JwtResponse();
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setToken(jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole()));

        return ResponseEntity.ok(response);
    }
}
