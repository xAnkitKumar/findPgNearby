package com.pgfinder.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference IDs (no direct entity dependency)
    @Column(nullable = false)
    private Long userId;   // Tenant who booked

    @Column(nullable = false)
    private Long pgId;     // PG being booked

    @Column(nullable = false)
    private Long roomId;   // Specific room inside PG

    // Booking details
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;  // PENDING, CONFIRMED, CANCELLED

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // Audit fields
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}