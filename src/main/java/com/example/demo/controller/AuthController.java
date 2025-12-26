package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserProfile;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserProfileService userService;
    private final UserProfileRepository userRepo;
    private final AuthenticationManager authManager;
    private final JwtUtil jwt;

    public AuthController(UserProfileService us, UserProfileRepository repo, AuthenticationManager am, JwtUtil jwt){
        this.userService = us;
        this.userRepo = repo;
        this.authManager = am;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest req){

        if(userRepo.existsByEmail(req.getEmail())) throw new BadRequestException("Email already used");

        UserProfile u = new UserProfile();
        u.setFullName(req.getFullName());
        u.setEmail(req.getEmail());
        u.setPassword(req.getPassword());
        u.setRole(req.getRole());
        u.setUserId("UID-"+UUID.randomUUID());

        u = userService.createUser(u);

        String token = jwt.generateToken(u.getId(), u.getEmail(), u.getRole());

        return ResponseEntity.ok(new JwtResponse(token, u.getId(), u.getEmail(), u.getRole()));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest req){

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        UserProfile u = userRepo.findByEmail(req.getEmail()).orElseThrow();
        if(!u.getActive()) throw new BadRequestException("Inactive account");

        String token = jwt.generateToken(u.getId(), u.getEmail(), u.getRole());

        return ResponseEntity.ok(new JwtResponse(token, u.getId(), u.getEmail(), u.getRole()));
    }
}
