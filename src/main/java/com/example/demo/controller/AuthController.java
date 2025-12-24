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
    private final UserProfileRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserProfileService userService,
                          UserProfileRepository userRepo,
                          AuthenticationManager authManager,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest req) {

        if (userRepo.existsByEmail(req.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        UserProfile user = new UserProfile();
        user.setUserId(UUID.randomUUID().toString());
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole() != null ? req.getRole() : "USER");
        user.setActive(true);

        UserProfile saved = userService.createUser(user);

        String token = jwtUtil.generateToken(saved.getId(), saved.getEmail(), saved.getRole());
        JwtResponse resp = new JwtResponse(saved.getId(), saved.getEmail(), saved.getRole(), token);

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        UserProfile user = userRepo.findByEmail(req.getEmail()).orElseThrow();
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
        JwtResponse resp = new JwtResponse(user.getId(), user.getEmail(), user.getRole(), token);

        return ResponseEntity.ok(resp);
    }
}
