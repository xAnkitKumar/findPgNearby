package com.pgfinder.pg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pgs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic details
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String pincode;

    // Geolocation
    private Double latitude;
    private Double longitude;

    // Pricing
    private Double pricePerMonth;

    // Owner reference (no direct User entity dependency)
    @Column(nullable = false)
    private Long ownerId;  // store owner’s ID, fetched via User Service API

    // Amenities (simple fields for MVP)
    private Boolean wifi;
    private Boolean food;
    private Boolean laundry;
    private Boolean parking;
    private Boolean ac;

    // Audit fields
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}