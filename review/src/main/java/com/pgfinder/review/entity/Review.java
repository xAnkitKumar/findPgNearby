package com.pgfinder.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign Keys (IDs only - microservice references)
    @Column(nullable = false)
    private Long pgId;  // Reference to PG entity

    @Column(nullable = false)
    private Long userId;  // Reference to User entity (reviewer)

    // Review content
    @Column(nullable = false)
    private Integer rating;  // 1-5 stars

    @Column(length = 1000)
    private String comment;

    // Review metadata
    private Integer helpfulCount = 0;  // Count of helpful votes

    @Enumerated(EnumType.STRING)
    private ReviewStatus status = ReviewStatus.PUBLISHED;  // PUBLISHED, FLAGGED, DELETED

    // Audit fields
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum ReviewStatus {
        PUBLISHED,
        FLAGGED,
        DELETED
    }
}
