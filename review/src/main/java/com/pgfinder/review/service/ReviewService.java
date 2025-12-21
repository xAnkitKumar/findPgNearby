package com.pgfinder.review.service;

import com.pgfinder.review.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    // Create a new review
    Review createReview(Review review);

    // Get all reviews
    List<Review> getAllReviews();

    // Get review by ID
    Optional<Review> getReviewById(Long id);

    // Get reviews by PG ID
    List<Review> getReviewsByPgId(Long pgId);

    // Get reviews by user ID (reviewer)
    List<Review> getReviewsByUserId(Long userId);

    // Get published reviews for a PG
    List<Review> getPublishedReviewsByPgId(Long pgId);

    // Get reviews by rating
    List<Review> getReviewsByRating(Integer rating);

    // Get reviews by status
    List<Review> getReviewsByStatus(Review.ReviewStatus status);

    // Update review
    Review updateReview(Long id, Review reviewDetails);

    // Delete review
    void deleteReview(Long id);

    // Check if user already reviewed a PG
    boolean hasUserReviewedPG(Long pgId, Long userId);

    // Count reviews for a PG
    long countReviewsByPgId(Long pgId);

    // Count published reviews for a PG
    long countPublishedReviewsByPgId(Long pgId);

    // Get average rating for a PG
    Double getAverageRatingByPgId(Long pgId);

    // Flag review as inappropriate
    Review flagReview(Long id);

    // Mark review as helpful
    Review markReviewAsHelpful(Long id);

}
