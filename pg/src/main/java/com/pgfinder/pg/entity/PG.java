package com.pgfinder.pg.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    /* ================= BASIC DETAILS ================= */

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 500)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String area; // Whitefield, Bellandur, etc.

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String pincode;

    @Enumerated(EnumType.STRING)
    private PgGender gender; // MEN, WOMEN, UNISEX

    /* ================= GEO ================= */

    private Double latitude;
    private Double longitude;

    /* ================= PRICING ================= */

    private Double pricePerMonth;

    private Boolean shortStayEnabled = false;
    private Double pricePerDay; // used only if short stay enabled

    /* ================= OCCUPANCY ================= */

    private Integer totalBeds;
    private Integer availableBeds;

    /* ================= OWNER ================= */

    @Column(nullable = false)
    private Long ownerId;

    /* ================= AMENITIES (MVP) ================= */

    private Boolean wifi;
    private Boolean food;
    private Boolean laundry;
    private Boolean parking;
    private Boolean ac;

    /* ================= STATUS ================= */

    @Enumerated(EnumType.STRING)
    private PgStatus status; // ACTIVE, INACTIVE

    /* ================= AUDIT ================= */

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) status = PgStatus.ACTIVE;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    public enum PgGender {
        MEN,
        WOMEN,
        UNISEX
    }
    public enum PgStatus {
        ACTIVE,
        INACTIVE
    }
    
    
}
