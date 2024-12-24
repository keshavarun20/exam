package com.example.exam.security;

import com.example.exam.model.User;
import com.example.exam.repo.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the repository
        User user = userRepo.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.singleton(authority))
                .build();
    }
}
