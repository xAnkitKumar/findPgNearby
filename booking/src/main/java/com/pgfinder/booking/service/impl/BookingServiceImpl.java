package com.pgfinder.booking.service.impl;

import com.pgfinder.booking.entity.Booking;
import com.pgfinder.booking.entity.BookingStatus;
import com.pgfinder.booking.repository.BookingRepository;
import com.pgfinder.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        // Check if PG is available for dates
        if (!isPgAvailableForDates(booking.getPgId(), booking.getStartDate(), booking.getEndDate())) {
            throw new RuntimeException("PG is not available for the selected dates");
        }
        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> getBookingsByTenantId(Long tenantId) {
        return bookingRepository.findByTenantIdOrderByBookedAtDesc(tenantId);
    }

    @Override
    public List<Booking> getBookingsByPgId(Long pgId) {
        return bookingRepository.findByPgIdOrderByBookedAtDesc(pgId);
    }

    @Override
    public List<Booking> getActiveBookingsByPgId(Long pgId) {
        return bookingRepository.findActiveBookingsByPgId(pgId);
    }

    @Override
    public List<Booking> getBookingsByStatus(BookingStatus status) {
        return bookingRepository.findByStatusOrderByBookedAtDesc(status);
    }

    @Override
    public boolean isPgAvailableForDates(Long pgId, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        return bookingRepository.isPgAvailableForDates(pgId, checkInDate, checkOutDate);
    }

    @Override
    public Booking updateBooking(Long id, Booking bookingDetails) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            if (bookingDetails.getStatus() != null) {
                booking.setStatus(bookingDetails.getStatus());
            }
            if (bookingDetails.getStartDate() != null) {
                booking.setStartDate(bookingDetails.getStartDate());
            }
            if (bookingDetails.getEndDate() != null) {
                booking.setEndDate(bookingDetails.getEndDate());
            }
            booking.setUpdatedAt(LocalDateTime.now());
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    @Override
    public Booking cancelBooking(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setStatus(BookingStatus.CANCELLED);
            booking.setUpdatedAt(LocalDateTime.now());
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    @Override
    public Booking confirmBooking(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setStatus(BookingStatus.CONFIRMED);
            booking.setUpdatedAt(LocalDateTime.now());
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    @Override
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> getBookingsByOwnerId(Long ownerId) {
        return bookingRepository.findBookingsByOwnerId(ownerId);
    }

    @Override
    public long countConfirmedBookingsByTenant(Long tenantId) {
        return bookingRepository.countByTenantIdAndStatus(tenantId, BookingStatus.CONFIRMED);
    }

}
