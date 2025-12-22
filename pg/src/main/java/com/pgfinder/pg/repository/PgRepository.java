package com.pgfinder.pg.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pgfinder.pg.entity.PG;
import com.pgfinder.pg.entity.PG.PgStatus;

public interface PgRepository extends JpaRepository<PG, Long> {

    // Find all active PGs with pagination
    Page<PG> findByStatus(PgStatus status, Pageable pageable);
    
    // Find PGs by city with pagination
    Page<PG> findByCityIgnoreCase(String city, Pageable pageable);
    
    // Find active PGs by city
    Page<PG> findByCityIgnoreCaseAndStatus(String city, PgStatus status, Pageable pageable);
    
    // Find PGs by city and area
    Page<PG> findByCityIgnoreCaseAndAreaIgnoreCase(String city, String area, Pageable pageable);
    
    // Find active PGs by city and area
    Page<PG> findByCityIgnoreCaseAndAreaIgnoreCaseAndStatus(
        String city, String area, PgStatus status, Pageable pageable);
    
    // Find PGs by gender type
    Page<PG> findByGender(PG.PgGender gender, Pageable pageable);
    
    // Find PGs by city and gender
    Page<PG> findByCityIgnoreCaseAndGender(String city, PG.PgGender gender, Pageable pageable);
    
    // Find PGs within price range
    Page<PG> findByPricePerMonthBetween(Double minPrice, Double maxPrice, Pageable pageable);
    
    // Find PGs by city within price range
    Page<PG> findByCityIgnoreCaseAndPricePerMonthBetween(
        String city, Double minPrice, Double maxPrice, Pageable pageable);
    
    // Find PGs by amenities (wifi)
    Page<PG> findByWifiTrue(Pageable pageable);
    
    // Find PGs by amenities (food)
    Page<PG> findByFoodTrue(Pageable pageable);
    
    // Find PGs by amenities (AC)
    Page<PG> findByAcTrue(Pageable pageable);
    
    // Find PGs by amenities (parking)
    Page<PG> findByParkingTrue(Pageable pageable);
    
    // Find PGs by amenities (laundry)
    Page<PG> findByLaundryTrue(Pageable pageable);
    
    // Find PGs by owner
    Page<PG> findByOwnerId(Long ownerId, Pageable pageable);
    
    // Find PGs with available beds
    Page<PG> findByAvailableBedsGreaterThan(Integer minAvailableBeds, Pageable pageable);
    
    // Find PGs in a specific city with available beds
    Page<PG> findByCityIgnoreCaseAndAvailableBedsGreaterThan(
        String city, Integer minAvailableBeds, Pageable pageable);
    
    // Custom query for searching by multiple criteria
    @Query("SELECT p FROM PG p WHERE " +
           "(:city IS NULL OR LOWER(p.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
           "(:area IS NULL OR LOWER(p.area) LIKE LOWER(CONCAT('%', :area, '%'))) AND " +
           "(:gender IS NULL OR p.gender = :gender) AND " +
           "(:minPrice IS NULL OR p.pricePerMonth >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.pricePerMonth <= :maxPrice) AND " +
           "(:wifi IS NULL OR p.wifi = :wifi) AND " +
           "(:food IS NULL OR p.food = :food) AND " +
           "(:ac IS NULL OR p.ac = :ac) AND " +
           "(:parking IS NULL OR p.parking = :parking) AND " +
           "(:laundry IS NULL OR p.laundry = :laundry) AND " +
           "(:shortStayEnabled IS NULL OR p.shortStayEnabled = :shortStayEnabled) AND " +
           "p.status = 'ACTIVE'")
    Page<PG> searchPgs(
        @Param("city") String city,
        @Param("area") String area,
        @Param("gender") PG.PgGender gender,
        @Param("minPrice") Double minPrice,
        @Param("maxPrice") Double maxPrice,
        @Param("wifi") Boolean wifi,
        @Param("food") Boolean food,
        @Param("ac") Boolean ac,
        @Param("parking") Boolean parking,
        @Param("laundry") Boolean laundry,
        @Param("shortStayEnabled") Boolean shortStayEnabled,
        Pageable pageable);
    
    // Find cities with active PGs (for dropdown/filter)
    @Query("SELECT DISTINCT p.city FROM PG p WHERE p.status = 'ACTIVE' ORDER BY p.city")
    List<String> findDistinctCities();
    
    // Find areas within a city with active PGs
    @Query("SELECT DISTINCT p.area FROM PG p WHERE LOWER(p.city) = LOWER(:city) AND p.status = 'ACTIVE' ORDER BY p.area")
    List<String> findDistinctAreasByCity(@Param("city") String city);
    
    // Count available beds by city
    @Query("SELECT SUM(p.availableBeds) FROM PG p WHERE LOWER(p.city) = LOWER(:city) AND p.status = 'ACTIVE'")
    Integer countAvailableBedsByCity(@Param("city") String city);
    
    // Find PGs with high occupancy (for recommendations)
    @Query("SELECT p FROM PG p WHERE " +
           "(p.totalBeds - p.availableBeds) * 100.0 / p.totalBeds >= :occupancyThreshold AND " +
           "p.status = 'ACTIVE'")
    Page<PG> findHighlyOccupiedPgs(
        @Param("occupancyThreshold") Double occupancyThreshold, 
        Pageable pageable);
    
    // Find nearby PGs using latitude/longitude (for future map feature)
    @Query(value = "SELECT * FROM pgs p WHERE " +
           "p.status = 'ACTIVE' AND " +
           "(6371 * acos(cos(radians(:latitude)) * cos(radians(p.latitude)) * " +
           "cos(radians(p.longitude) - radians(:longitude)) + sin(radians(:latitude)) * " +
           "sin(radians(p.latitude)))) <= :radiusInKm " +
           "ORDER BY (6371 * acos(cos(radians(:latitude)) * cos(radians(p.latitude)) * " +
           "cos(radians(p.longitude) - radians(:longitude)) + sin(radians(:latitude)) * " +
           "sin(radians(p.latitude))))",
           nativeQuery = true)
    Page<PG> findNearbyPgs(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude,
        @Param("radiusInKm") Double radiusInKm,
        Pageable pageable);
    
    // Find PGs created recently
    Page<PG> findByCreatedAtAfter(java.time.LocalDateTime date, Pageable pageable);
    
    // Find PGs updated recently
    Page<PG> findByUpdatedAtAfter(java.time.LocalDateTime date, Pageable pageable);
}