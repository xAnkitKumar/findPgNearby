package com.pgfinder.pg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pgfinder.pg.entity.PG;

import java.util.List;

@Repository
public interface PgRepository extends JpaRepository<PG, Long> {

    // Find by owner ID
    List<PG> findByOwnerIdOrderByCreatedAtDesc(Long ownerId);

    // Find by location
    List<PG> findByLocationContainingIgnoreCase(String location);

    // Find by location and available
    List<PG> findByLocationContainingIgnoreCaseAndAvailableTrue(String location);

    // Find by budget range
    List<PG> findByRentBetweenOrderByRentAsc(Double minRent, Double maxRent);

    // Find by budget and available
    List<PG> findByRentBetweenAndAvailableTrueOrderByRentAsc(Double minRent, Double maxRent);

    // Find by location and budget
    @Query("SELECT p FROM PG p WHERE LOWER(p.location) LIKE LOWER(CONCAT('%', :location, '%')) " +
           "AND p.rent BETWEEN :minRent AND :maxRent AND p.available = true ORDER BY p.rent ASC")
    List<PG> findByLocationAndBudget(@Param("location") String location, 
                                     @Param("minRent") Double minRent, 
                                     @Param("maxRent") Double maxRent);

    // Find available PGs
    List<PG> findByAvailableTrueOrderByCreatedAtDesc();

    // Find available PGs in location
    List<PG> findByLocationContainingIgnoreCaseAndAvailableTrueOrderByRentAsc(String location);

    // Count available PGs
    long countByAvailableTrue();

    // Count PGs by owner
    long countByOwnerId(Long ownerId);

    // Find PGs with specific amenity (contains)
    List<PG> findByAmenitiesContainingIgnoreCase(String amenity);

    // Check if location has available PGs
    boolean existsByLocationContainingIgnoreCaseAndAvailableTrue(String location);

    // Find by owner and available
    List<PG> findByOwnerIdAndAvailableTrueOrderByCreatedAtDesc(Long ownerId);

}
