// package com.example.demo.security;

// import com.example.demo.entity.UserProfile;
// import com.example.demo.repository.UserProfileRepository;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import java.util.Collections;

// @Service
// public class CustomerUserDetailsService implements UserDetailsService {

//     private final UserProfileRepository userProfileRepository;

//     public CustomerUserDetailsService(UserProfileRepository userProfileRepository) {
//         this.userProfileRepository = userProfileRepository;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username)
//             throws UsernameNotFoundException {

//         UserProfile user = userProfileRepository
//                 .findByEmail(username)
//                 .orElseThrow(() ->
//                         new UsernameNotFoundException(
//                                 "User not found with email: " + username
//                         )
//                 );

//         return new User(
//                 user.getEmail(),
//                 user.getPassword(),
//                 user.isActive(),   // enabled
//                 true,              // accountNonExpired
//                 true,              // credentialsNonExpired
//                 true,              // accountNonLocked
//                 Collections.emptyList()
//         );
//     }
// }
