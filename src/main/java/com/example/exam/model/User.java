package com.example.exam.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name="password",nullable = false)
    private String password;

}
