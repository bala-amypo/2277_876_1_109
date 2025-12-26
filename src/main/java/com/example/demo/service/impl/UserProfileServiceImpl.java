package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; 

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repo;
    private final PasswordEncoder encoder;

    public UserProfileServiceImpl(UserProfileRepository repo, PasswordEncoder encoder){
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public UserProfile createUser(UserProfile p){
        if(repo.existsByEmail(p.getEmail())) throw new BadRequestException("Email exists");
        if(repo.existsByUserId(p.getUserId())) throw new BadRequestException("UserID exists");

        p.setPassword( encoder.encode(p.getPassword()) );
        return repo.save(p);
    }

    @Override
    public UserProfile getUserById(Long id){
        return repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<UserProfile> getAllUsers(){
        return repo.findAll();
    }
}
