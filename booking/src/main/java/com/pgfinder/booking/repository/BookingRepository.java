package com.pgfinder.booking.repository;

import com.pgfinder.booking.entity.Booking;
import com.pgfinder.booking.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find bookings by tenant (user)
    List<Booking> findByTenantIdOrderByBookedAtDesc(Long tenantId);

    // Find bookings by PG
    List<Booking> findByPgIdOrderByBookedAtDesc(Long pgId);

    // Find active bookings for a PG (not cancelled/completed)
    @Query("SELECT b FROM Booking b WHERE b.pgId = :pgId AND b.status IN ('PENDING', 'CONFIRMED') ORDER BY b.bookedAt DESC")
    List<Booking> findActiveBookingsByPgId(@Param("pgId") Long pgId);

    // Find bookings by status
    List<Booking> findByStatusOrderByBookedAtDesc(BookingStatus status);

    // Find pending bookings
    List<Booking> findByStatusAndTenantIdOrderByBookedAtDesc(BookingStatus status, Long tenantId);

    // Find bookings within date range
    @Query("SELECT b FROM Booking b WHERE b.checkInDate >= :startDate AND b.checkOutDate <= :endDate")
    List<Booking> findBookingsByDateRange(@Param("startDate") LocalDateTime startDate, 
                                          @Param("endDate") LocalDateTime endDate);

    // Find overlapping bookings for a PG
    @Query("SELECT b FROM Booking b WHERE b.pgId = :pgId AND b.status != 'CANCELLED' " +
           "AND b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate")
    List<Booking> findOverlappingBookings(@Param("pgId") Long pgId, 
                                          @Param("checkInDate") LocalDateTime checkInDate, 
                                          @Param("checkOutDate") LocalDateTime checkOutDate);

    // Check if PG is available for dates
    @Query("SELECT COUNT(b) = 0 FROM Booking b WHERE b.pgId = :pgId AND b.status != 'CANCELLED' " +
           "AND b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate")
    boolean isPgAvailableForDates(@Param("pgId") Long pgId, 
                                  @Param("checkInDate") LocalDateTime checkInDate, 
                                  @Param("checkOutDate") LocalDateTime checkOutDate);

    // Count confirmed bookings for tenant
    long countByTenantIdAndStatus(Long tenantId, BookingStatus status);

    // Find bookings by owner (via PG)
    @Query("SELECT b FROM Booking b WHERE b.pgId IN (SELECT p.id FROM Pg p WHERE p.ownerId = :ownerId) " +
           "ORDER BY b.bookedAt DESC")
    List<Booking> findBookingsByOwnerId(@Param("ownerId") Long ownerId);

    // Find recent bookings
    List<Booking> findByOrderByBookedAtDesc();

}
