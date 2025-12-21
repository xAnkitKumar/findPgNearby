package com.pgfinder.review.service.impl;

import com.pgfinder.review.entity.Review;
import com.pgfinder.review.repository.ReviewRepository;
import com.pgfinder.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review review) {
        // Check if user already reviewed this PG
        if (hasUserReviewedPG(review.getPgId(), review.getUserId())) {
            throw new RuntimeException("User has already reviewed this PG");
        }
        review.setStatus(Review.ReviewStatus.PUBLISHED);
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> getReviewsByPgId(Long pgId) {
        return reviewRepository.findByPgIdOrderByCreatedAtDesc(pgId);
    }

    @Override
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public List<Review> getPublishedReviewsByPgId(Long pgId) {
        return reviewRepository.findByPgIdAndStatusOrderByCreatedAtDesc(pgId, Review.ReviewStatus.PUBLISHED);
    }

    @Override
    public List<Review> getReviewsByRating(Integer rating) {
        return reviewRepository.findByRatingOrderByCreatedAtDesc(rating);
    }

    @Override
    public List<Review> getReviewsByStatus(Review.ReviewStatus status) {
        return reviewRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    @Override
    public Review updateReview(Long id, Review reviewDetails) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            if (reviewDetails.getRating() != null) {
                review.setRating(reviewDetails.getRating());
            }
            if (reviewDetails.getComment() != null) {
                review.setComment(reviewDetails.getComment());
            }
            if (reviewDetails.getStatus() != null) {
                review.setStatus(reviewDetails.getStatus());
            }
            review.setUpdatedAt(LocalDateTime.now());
            return reviewRepository.save(review);
        } else {
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }

    @Override
    public boolean hasUserReviewedPG(Long pgId, Long userId) {
        return reviewRepository.existsByPgIdAndUserId(pgId, userId);
    }

    @Override
    public long countReviewsByPgId(Long pgId) {
        return reviewRepository.countByPgId(pgId);
    }

    @Override
    public long countPublishedReviewsByPgId(Long pgId) {
        return reviewRepository.countByPgIdAndStatus(pgId, Review.ReviewStatus.PUBLISHED);
    }

    @Override
    public Double getAverageRatingByPgId(Long pgId) {
        return reviewRepository.getAverageRatingByPgId(pgId);
    }

    @Override
    public Review flagReview(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setStatus(Review.ReviewStatus.FLAGGED);
            review.setUpdatedAt(LocalDateTime.now());
            return reviewRepository.save(review);
        } else {
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

    @Override
    public Review markReviewAsHelpful(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setHelpfulCount((review.getHelpfulCount() != null ? review.getHelpfulCount() : 0) + 1);
            review.setUpdatedAt(LocalDateTime.now());
            return reviewRepository.save(review);
        } else {
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

}
