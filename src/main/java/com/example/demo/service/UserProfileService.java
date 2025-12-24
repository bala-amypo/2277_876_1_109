package com.example.demo.service;

import java.util.*;
import com.example.demo.entity.*;

public interface UserProfileService {
    UserProfile createUser(UserProfile u);
    UserProfile getUserById(Long id);
    List<UserProfile> getAllUsers();
}
