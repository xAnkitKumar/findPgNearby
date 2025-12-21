package com.pgfinder.pg.service;

import com.pgfinder.pg.entity.PG;

import java.util.List;
import java.util.Optional;

public interface PgService {

    // Create a new PG
    PG createPG(PG pg);

    // Get all PGs
    List<PG> getAllPGs();

    // Get PG by ID
    Optional<PG> getPGById(Long id);

    // Get PGs by owner ID
    List<PG> getPGsByOwnerId(Long ownerId);

    // Get PGs by location
    List<PG> getPGsByLocation(String location);

    // Get PGs by location and budget
    List<PG> getPGsByLocationAndBudget(String location, Double minBudget, Double maxBudget);

    // Get PGs by budget range
    List<PG> getPGsByBudgetRange(Double minBudget, Double maxBudget);

    // Get available PGs
    List<PG> getAvailablePGs();

    // Get available PGs in location
    List<PG> getAvailablePGsByLocation(String location);

    // Get PGs by city
    List<PG> getPGsByCity(String city);

    // Update PG
    PG updatePG(Long id, PG pgDetails);

    // Delete PG
    void deletePG(Long id);

    // Search PGs by amenity
    List<PG> searchPGsByAmenity(String amenity);

    // Count available PGs
    long countAvailablePGs();

    // Count PGs by owner
    long countPGsByOwner(Long ownerId);

}
