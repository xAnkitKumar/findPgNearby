
import { http } from '@/services/http';
import type { Booking, BookingFilters, CreateBookingDto } from './types';

export const bookingApi = {
  // Create a new booking
  createBooking: async (data: CreateBookingDto): Promise<Booking> => {
    const response = await http.post('/bookings', data);
    return response.data;
  },

  // Get user's bookings
  getMyBookings: async (filters?: BookingFilters): Promise<Booking[]> => {
    const response = await http.get('/bookings/my-bookings', { params: filters });
    return response.data;
  },

  // Get booking by ID
  getBookingById: async (id: string): Promise<Booking> => {
    const response = await http.get(`/bookings/${id}`);
    return response.data;
  },

  // Cancel booking
  cancelBooking: async (id: string): Promise<Booking> => {
    const response = await http.patch(`/bookings/${id}/cancel`);
    return response.data;
  },

  // Update booking
  updateBooking: async (id: string, data: Partial<Booking>): Promise<Booking> => {
    const response = await http.patch(`/bookings/${id}`, data);
    return response.data;
  },

  // Check availability
  checkAvailability: async (pgId: string, checkIn: string, checkOut: string): Promise<boolean> => {
    const response = await http.get('/bookings/check-availability', {
      params: { pgId, checkIn, checkOut }
    });
    return response.data.available;
  }
};