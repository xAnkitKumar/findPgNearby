package com.pgfinder.user.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic details
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;  // store hashed password, never plain text

    // Role: tenant / owner / admin
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Preferences (budget, location)
    private String preferredLocation;
    private Double budget;

    // Audit fields
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

   
}
