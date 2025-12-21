package com.pgfinder.booking.service;

import com.pgfinder.booking.entity.Booking;
import com.pgfinder.booking.entity.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingService {

    // Create a new booking
    Booking createBooking(Booking booking);

    // Get all bookings
    List<Booking> getAllBookings();

    // Get booking by ID
    Optional<Booking> getBookingById(Long id);

    // Get bookings by tenant ID
    List<Booking> getBookingsByTenantId(Long tenantId);

    // Get bookings by PG ID
    List<Booking> getBookingsByPgId(Long pgId);

    // Get active bookings by PG ID
    List<Booking> getActiveBookingsByPgId(Long pgId);

    // Get bookings by status
    List<Booking> getBookingsByStatus(BookingStatus status);

    // Check if PG is available for dates
    boolean isPgAvailableForDates(Long pgId, LocalDateTime checkInDate, LocalDateTime checkOutDate);

    // Update booking
    Booking updateBooking(Long id, Booking booking);

    // Cancel booking
    Booking cancelBooking(Long id);

    // Confirm booking
    Booking confirmBooking(Long id);

    // Delete booking
    void deleteBooking(Long id);

    // Get bookings by owner
    List<Booking> getBookingsByOwnerId(Long ownerId);

    // Count confirmed bookings by tenant
    long countConfirmedBookingsByTenant(Long tenantId);

}
