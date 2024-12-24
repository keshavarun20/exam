package com.example.exam.controller;

import com.example.exam.model.User;
import com.example.exam.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        );

        Authentication authentication =authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = JwtUtil.generateToken((org.springframework.security.core.userdetails.User) authentication.getPrincipal());

        return ResponseEntity.ok(jwtToken);
    }
}
