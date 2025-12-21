package com.pgfinder.review.controller;

import com.pgfinder.review.entity.Review;
import com.pgfinder.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Create a new review
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        try {
            Review createdReview = reviewService.createReview(review);
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Get all reviews
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        try {
            List<Review> reviews = reviewService.getAllReviews();
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get review by ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        try {
            Optional<Review> review = reviewService.getReviewById(id);
            return review.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get reviews by PG ID
    @GetMapping("/pg/{pgId}")
    public ResponseEntity<List<Review>> getReviewsByPgId(@PathVariable Long pgId) {
        try {
            List<Review> reviews = reviewService.getReviewsByPgId(pgId);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get reviews by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
        try {
            List<Review> reviews = reviewService.getReviewsByUserId(userId);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get published reviews for a PG
    @GetMapping("/pg/{pgId}/published")
    public ResponseEntity<List<Review>> getPublishedReviewsByPgId(@PathVariable Long pgId) {
        try {
            List<Review> reviews = reviewService.getPublishedReviewsByPgId(pgId);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get reviews by rating
    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Review>> getReviewsByRating(@PathVariable Integer rating) {
        try {
            List<Review> reviews = reviewService.getReviewsByRating(rating);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get reviews by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Review>> getReviewsByStatus(@PathVariable Review.ReviewStatus status) {
        try {
            List<Review> reviews = reviewService.getReviewsByStatus(status);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update review
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        try {
            Review updatedReview = reviewService.updateReview(id, reviewDetails);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Delete review
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            reviewService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Check if user already reviewed a PG
    @GetMapping("/pg/{pgId}/user/{userId}/exists")
    public ResponseEntity<Boolean> hasUserReviewedPG(@PathVariable Long pgId, @PathVariable Long userId) {
        try {
            boolean hasReviewed = reviewService.hasUserReviewedPG(pgId, userId);
            return new ResponseEntity<>(hasReviewed, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Count reviews for a PG
    @GetMapping("/pg/{pgId}/count")
    public ResponseEntity<Long> countReviewsByPgId(@PathVariable Long pgId) {
        try {
            long count = reviewService.countReviewsByPgId(pgId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Count published reviews for a PG
    @GetMapping("/pg/{pgId}/count/published")
    public ResponseEntity<Long> countPublishedReviewsByPgId(@PathVariable Long pgId) {
        try {
            long count = reviewService.countPublishedReviewsByPgId(pgId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get average rating for a PG
    @GetMapping("/pg/{pgId}/average-rating")
    public ResponseEntity<Double> getAverageRatingByPgId(@PathVariable Long pgId) {
        try {
            Double averageRating = reviewService.getAverageRatingByPgId(pgId);
            return new ResponseEntity<>(averageRating != null ? averageRating : 0.0, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0.0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Flag review as inappropriate
    @PutMapping("/{id}/flag")
    public ResponseEntity<Review> flagReview(@PathVariable Long id) {
        try {
            Review flaggedReview = reviewService.flagReview(id);
            return new ResponseEntity<>(flaggedReview, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Mark review as helpful
    @PutMapping("/{id}/helpful")
    public ResponseEntity<Review> markReviewAsHelpful(@PathVariable Long id) {
        try {
            Review helpfulReview = reviewService.markReviewAsHelpful(id);
            return new ResponseEntity<>(helpfulReview, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
