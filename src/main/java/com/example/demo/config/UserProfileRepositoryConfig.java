package com.example.demo.config;

import com.example.demo.repository.UserProfileRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class UserProfileRepositoryConfig {

    @Bean
    public UserProfileRepository userProfileRepository() {
        return mock(UserProfileRepository.class);
    }
}
