package com.pgfinder.booking.controller;

import com.pgfinder.booking.entity.Booking;
import com.pgfinder.booking.entity.BookingStatus;
import com.pgfinder.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create a new booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        try {
            Booking createdBooking = bookingService.createBooking(booking);
            return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Get all bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        try {
            List<Booking> bookings = bookingService.getAllBookings();
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        try {
            Optional<Booking> booking = bookingService.getBookingById(id);
            return booking.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get bookings by tenant ID
    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<Booking>> getBookingsByTenantId(@PathVariable Long tenantId) {
        try {
            List<Booking> bookings = bookingService.getBookingsByTenantId(tenantId);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Count confirmed bookings for tenant
    @GetMapping("/tenant/{tenantId}/count")
    public ResponseEntity<Long> countConfirmedBookingsByTenant(@PathVariable Long tenantId) {
        try {
            long count = bookingService.countConfirmedBookingsByTenant(tenantId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get bookings by PG ID
    @GetMapping("/pg/{pgId}")
    public ResponseEntity<List<Booking>> getBookingsByPgId(@PathVariable Long pgId) {
        try {
            List<Booking> bookings = bookingService.getBookingsByPgId(pgId);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get active bookings for a PG
    @GetMapping("/pg/{pgId}/active")
    public ResponseEntity<List<Booking>> getActiveBookingsByPgId(@PathVariable Long pgId) {
        try {
            List<Booking> activeBookings = bookingService.getActiveBookingsByPgId(pgId);
            return new ResponseEntity<>(activeBookings, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get bookings by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus status) {
        try {
            List<Booking> bookings = bookingService.getBookingsByStatus(status);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Check if PG is available for dates
    @GetMapping("/pg/{pgId}/availability")
    public ResponseEntity<Boolean> isPgAvailableForDates(
            @PathVariable Long pgId,
            @RequestParam LocalDateTime checkInDate,
            @RequestParam LocalDateTime checkOutDate) {
        try {
            boolean available = bookingService.isPgAvailableForDates(pgId, checkInDate, checkOutDate);
            return new ResponseEntity<>(available, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update booking
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        try {
            Booking updatedBooking = bookingService.updateBooking(id, booking);
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Cancel booking
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
        try {
            Booking cancelledBooking = bookingService.cancelBooking(id);
            return new ResponseEntity<>(cancelledBooking, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Confirm booking
    @PutMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id) {
        try {
            Booking confirmedBooking = bookingService.confirmBooking(id);
            return new ResponseEntity<>(confirmedBooking, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Delete booking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get bookings by owner (via PG)
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Booking>> getBookingsByOwnerId(@PathVariable Long ownerId) {
        try {
            List<Booking> bookings = bookingService.getBookingsByOwnerId(ownerId);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
