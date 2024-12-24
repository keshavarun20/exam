package com.example.exam.controller;

import com.example.exam.model.User;
import com.example.exam.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("user/create")
    public ResponseEntity<String> createUser(@RequestBody User user){

        Optional<User> optionalUser = userRepo.findById(user.getUsername());

        if (!optionalUser.isPresent()){
            User newUser = new User(user.getUsername(),user.getRole(),passwordEncoder.encode(user.getPassword()));
            userRepo.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User Already Exist");
        }

    }
}
