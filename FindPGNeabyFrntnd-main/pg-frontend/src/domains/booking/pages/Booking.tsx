import { ArrowLeft, Car, Coffee, MapPin, Star, Users, Wifi } from 'lucide-react';
import React, { useEffect, useState } from 'react';
import { toast } from 'sonner'; // ✅ Import from sonner instead

import { Button } from '@/components/ui/button';
import { pgApi } from '@/domains/pg/api';
import type { PgDetails } from '@/domains/pg/types';

import { useNavigate, useParams } from 'react-router-dom';
import { bookingApi } from '../api';
import { BookingForm } from '../components/BookingForm';
import { DateSelector } from '../components/DateSelector';
import { PriceBreakup } from '../components/PriceBreakup';
import '../css/Booking.css';
import { useBookingStore } from '../store';

export const BookingPage: React.FC = () => {
  const { pgId } = useParams<{ pgId: string }>();
  const navigate = useNavigate();
  // ❌ REMOVE: const { toast } = useToast(); // Delete this line
  const { createBooking, loading } = useBookingStore();
  
  const [pgDetails, setPgDetails] = useState<PgDetails | null>(null);
  const [checkIn, setCheckIn] = useState<Date>();
  const [checkOut, setCheckOut] = useState<Date>();
  const [guests, setGuests] = useState(1);
  const [isAvailable, setIsAvailable] = useState(true);
  const [step, setStep] = useState<'dates' | 'details' | 'confirm'>('dates');

  useEffect(() => {
    if (pgId) {
      fetchPgDetails();
    }
  }, [pgId]);

  const fetchPgDetails = async () => {
    try {
      const details = await pgApi.getPgById(pgId!);
      setPgDetails(details);
    } catch (error) {
      // ✅ NEW: Use sonner's toast function
      toast.error("Failed to load PG details");
      navigate('/search');
    }
  };

  const checkAvailability = async () => {
    if (!pgId || !checkIn || !checkOut) return;
    
    try {
      const available = await bookingApi.checkAvailability(pgId, checkIn.toISOString(), checkOut.toISOString());
      setIsAvailable(available);
      
      if (available) {
        setStep('details');
        // ✅ NEW: Use sonner's toast function
        toast.success("These dates are available for booking!");
      } else {
        // ✅ NEW: Use sonner's toast function
        toast.error("These dates are already booked");
      }
    } catch (error) {
      // ✅ NEW: Use sonner's toast function
      toast.error("Failed to check availability");
    }
  };

  const handleBookingSubmit = async (formData: any) => {
    if (!pgId || !checkIn || !checkOut) return;

    try {
      const bookingData = {
        pgId,
        checkIn: checkIn.toISOString(),
        checkOut: checkOut.toISOString(),
        guests,
        specialRequests: formData.specialRequests,
        paymentMethod: formData.paymentMethod
      };

      const booking = await createBooking(bookingData);
      
      // ✅ NEW: Use sonner's toast function
      toast.success("Your booking has been successfully created!");
      
      navigate(`/booking/confirmation/${booking.id}`);
    } catch (error: any) {
      // ✅ NEW: Use sonner's toast function
      toast.error(error.message || "Booking failed. Please try again");
    }
  };

  const calculateNights = () => {
    if (!checkIn || !checkOut) return 0;
    return Math.ceil((checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24));
  };

  if (!pgDetails) {
    return (
      <div className="loading-container">
        <div className="loading-spinner"></div>
        <p>Loading PG details...</p>
      </div>
    );
  }

  return (
    <div className="booking-page">
      {/* Header */}
      <header className="booking-header">
        <Button
          variant="ghost"
          size="sm"
          onClick={() => navigate(-1)}
          className="back-button"
        >
          <ArrowLeft className="back-icon" />
          Back
        </Button>
        <h1 className="page-title">Complete Your Booking</h1>
      </header>

      <div className="booking-layout">
        {/* Left Column - Booking Form */}
        <main className="booking-main">
          {/* PG Info Card */}
          <div className="pg-info-card">
            <img
              src={pgDetails.thumbnail || '/api/placeholder/400/300'}
              alt={pgDetails.name}
              className="pg-thumbnail"
            />
            <div className="pg-info-content">
              <div>
                <h2 className="pg-name">{pgDetails.name}</h2>
                <div className="pg-location">
                  <MapPin className="location-icon" />
                  <span>{pgDetails.address}</span>
                </div>
                <div className="pg-rating">
                  <Star className="star-icon" />
                  <span>{pgDetails.rating || 4.5}</span>
                  <span className="review-count">({pgDetails.reviewCount || 120} reviews)</span>
                </div>
              </div>
              <div className="pg-amenities">
                <div className="amenity">
                  <Wifi className="amenity-icon" />
                  <span>WiFi</span>
                </div>
                <div className="amenity">
                  <Users className="amenity-icon" />
                  <span>{pgDetails.shared ? 'Shared' : 'Private'}</span>
                </div>
                <div className="amenity">
                  <Coffee className="amenity-icon" />
                  <span>Food</span>
                </div>
                <div className="amenity">
                  <Car className="amenity-icon" />
                  <span>Parking</span>
                </div>
              </div>
            </div>
          </div>

          {/* Booking Steps */}
          <div className="booking-steps">
            <div className={`step ${step === 'dates' ? 'active' : ''}`}>
              <div className="step-number">1</div>
              <span className="step-label">Select Dates</span>
            </div>
            <div className={`step ${step === 'details' ? 'active' : ''}`}>
              <div className="step-number">2</div>
              <span className="step-label">Guest Details</span>
            </div>
            <div className={`step ${step === 'confirm' ? 'active' : ''}`}>
              <div className="step-number">3</div>
              <span className="step-label">Confirmation</span>
            </div>
          </div>

          {/* Step 1: Date Selection */}
          {step === 'dates' && (
            <div className="step-content">
              <h2 className="step-title">Select Your Stay Dates</h2>
              <DateSelector
                checkIn={checkIn}
                checkOut={checkOut}
                onCheckInChange={setCheckIn}
                onCheckOutChange={setCheckOut}
              />
              <div className="guests-selector">
                <label className="guests-label">Number of Guests</label>
                <div className="guests-controls">
                  <Button
                    variant="outline"
                    size="icon"
                    onClick={() => setGuests(prev => Math.max(1, prev - 1))}
                    disabled={guests <= 1}
                    className="guest-button"
                  >
                    -
                  </Button>
                  <span className="guest-count">{guests} Guest{guests > 1 ? 's' : ''}</span>
                  <Button
                    variant="outline"
                    size="icon"
                    onClick={() => setGuests(prev => Math.min(pgDetails.maxOccupancy || 4, prev + 1))}
                    disabled={guests >= (pgDetails.maxOccupancy || 4)}
                    className="guest-button"
                  >
                    +
                  </Button>
                </div>
              </div>
              <Button
                onClick={checkAvailability}
                disabled={!checkIn || !checkOut}
                className="continue-button"
                size="lg"
              >
                Check Availability & Continue
              </Button>
            </div>
          )}

          {/* Step 2: Guest Details */}
          {step === 'details' && (
            <div className="step-content">
              <h2 className="step-title">Enter Your Details</h2>
              <BookingForm
                onSubmit={handleBookingSubmit}
                loading={loading}
              />
            </div>
          )}
        </main>

        {/* Right Column - Price Summary */}
        <aside className="booking-sidebar">
          <div className="sticky-summary">
            <PriceBreakup
              pricePerNight={pgDetails.pricePerNight}
              nights={calculateNights()}
              guests={guests}
              discount={checkIn && checkOut ? 200 : 0}
            />
            
            <div className="booking-highlights">
              <h3 className="highlights-title">What's Included</h3>
              <ul className="highlights-list">
                <li>✓ Free high-speed WiFi</li>
                <li>✓ Daily housekeeping</li>
                <li>✓ Breakfast included</li>
                <li>✓ 24/7 security</li>
                <li>✓ Free cancellation until check-in</li>
              </ul>
            </div>

            <div className="support-section">
              <h3 className="support-title">Need Help?</h3>
              <p className="support-text">Our support team is available 24/7 to assist you.</p>
              <Button variant="outline" className="support-button">
                Contact Support
              </Button>
            </div>
          </div>
        </aside>
      </div>
    </div>
  );
};