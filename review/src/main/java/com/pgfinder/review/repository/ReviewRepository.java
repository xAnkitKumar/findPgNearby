package com.pgfinder.review.repository;

import com.pgfinder.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find reviews by PG ID
    List<Review> findByPgIdOrderByCreatedAtDesc(Long pgId);

    // Find reviews by User ID (reviewer)
    List<Review> findByUserIdOrderByCreatedAtDesc(Long userId);

    // Find published reviews for a PG
    List<Review> findByPgIdAndStatusOrderByCreatedAtDesc(Long pgId, Review.ReviewStatus status);

    // Find reviews by rating
    List<Review> findByRatingOrderByCreatedAtDesc(Integer rating);

    // Count reviews for a PG
    long countByPgId(Long pgId);

    // Count published reviews for a PG
    long countByPgIdAndStatus(Long pgId, Review.ReviewStatus status);

    // Average rating for a PG (custom query)
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.pgId = :pgId AND r.status = 'PUBLISHED'")
    Double getAverageRatingByPgId(@Param("pgId") Long pgId);

    // Find reviews by status
    List<Review> findByStatusOrderByCreatedAtDesc(Review.ReviewStatus status);

    // Check if user already reviewed a PG
    boolean existsByPgIdAndUserId(Long pgId, Long userId);

}
