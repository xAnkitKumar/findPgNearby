package com.pgfinder.pg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pgfinder.pg.entity.PgImage;

public interface PgImageRepository extends JpaRepository<PgImage, Long> {

    List<PgImage> findByPgIdOrderBySortOrderAsc(Long pgId);

    Optional<PgImage> findByPgIdAndIsPrimaryTrue(Long pgId);
    
    @Query("SELECT pi FROM PgImage pi WHERE pi.pgId IN :pgIds AND pi.isPrimary = true")
    List<PgImage> findPrimaryImagesByPgIds(@Param("pgIds") List<Long> pgIds);
    
    @Query("SELECT pi FROM PgImage pi WHERE pi.pgId IN :pgIds AND " +
           "(pi.isPrimary = true OR pi.id = (SELECT MIN(pi2.id) FROM PgImage pi2 WHERE pi2.pgId = pi.pgId))")
    List<PgImage> findPrimaryOrFirstImagesByPgIds(@Param("pgIds") List<Long> pgIds);
    
    // Add this new method to fetch all images for multiple PGs
    @Query("SELECT pi FROM PgImage pi WHERE pi.pgId IN :pgIds ORDER BY pi.pgId, pi.sortOrder ASC")
    List<PgImage> findByPgIdInOrderBySortOrderAsc(@Param("pgIds") List<Long> pgIds);
}