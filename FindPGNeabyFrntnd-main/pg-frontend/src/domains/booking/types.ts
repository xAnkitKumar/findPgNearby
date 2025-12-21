export interface Booking {
    id: string;
    pgId: string;
    userId: string;
    checkIn: string;
    checkOut: string;
    totalNights: number;
    guests: number;
    totalAmount: number;
    status: 'pending' | 'confirmed' | 'cancelled' | 'completed';
    paymentStatus: 'pending' | 'paid' | 'refunded' | 'failed';
    createdAt: string;
    updatedAt: string;
    specialRequests?: string;
    paymentMethod?: 'card' | 'upi' | 'cash';
    pgDetails?: {
      name: string;
      address: string;
      thumbnail: string;
      pricePerNight: number;
    };
  }
  
  export interface CreateBookingDto {
    pgId: string;
    checkIn: string;
    checkOut: string;
    guests: number;
    specialRequests?: string;
    paymentMethod?: 'card' | 'upi' | 'cash';
  }
  
  export interface BookingFilters {
    status?: Booking['status'];
    dateFrom?: string;
    dateTo?: string;
    pgId?: string;
  }