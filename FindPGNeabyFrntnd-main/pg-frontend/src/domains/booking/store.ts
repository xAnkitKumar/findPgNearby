import { create } from 'zustand';
import { bookingApi } from './api';
import type { Booking, BookingFilters, CreateBookingDto } from './types';

interface BookingStore {
  bookings: Booking[];
  currentBooking: Booking | null;
  loading: boolean;
  error: string | null;
  
  // Actions
  fetchBookings: (filters?: BookingFilters) => Promise<void>;
  createBooking: (data: CreateBookingDto) => Promise<Booking>;
  cancelBooking: (id: string) => Promise<void>;
  setCurrentBooking: (booking: Booking | null) => void;
  clearError: () => void;
}

export const useBookingStore = create<BookingStore>((set, get) => ({
  bookings: [],
  currentBooking: null,
  loading: false,
  error: null,

  fetchBookings: async (filters) => {
    set({ loading: true, error: null });
    try {
      const bookings = await bookingApi.getMyBookings(filters);
      set({ bookings, loading: false });
    } catch (error: any) {
      set({ error: error.message, loading: false });
    }
  },

  createBooking: async (data) => {
    set({ loading: true, error: null });
    try {
      const booking = await bookingApi.createBooking(data);
      set(state => ({ 
        bookings: [booking, ...state.bookings],
        loading: false 
      }));
      return booking;
    } catch (error: any) {
      set({ error: error.message, loading: false });
      throw error;
    }
  },

  cancelBooking: async (id) => {
    set({ loading: true, error: null });
    try {
      await bookingApi.cancelBooking(id);
      set(state => ({
        bookings: state.bookings.map(booking =>
          booking.id === id 
            ? { ...booking, status: 'cancelled' }
            : booking
        ),
        loading: false
      }));
    } catch (error: any) {
      set({ error: error.message, loading: false });
      throw error;
    }
  },

  setCurrentBooking: (booking) => {
    set({ currentBooking: booking });
  },

  clearError: () => {
    set({ error: null });
  }
}));