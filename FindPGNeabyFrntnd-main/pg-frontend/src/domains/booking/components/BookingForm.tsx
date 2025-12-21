import { Banknote, CreditCard, Mail, MessageSquare, Phone, User, Wallet } from 'lucide-react';
import React, { useState } from 'react';
import { toast } from 'sonner'; // ✅ Import from sonner instead

import { Button } from '@/components/ui/button';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group';
import { Textarea } from '@/components/ui/textarea';
import '../css/bookingForm.css';

interface GuestInfo {
  name: string;
  email: string;
  phone: string;
}

interface BookingFormProps {
  initialData?: Partial<GuestInfo>;
  onSubmit: (data: GuestInfo & { specialRequests: string; paymentMethod: string }) => void;
  loading?: boolean;
}

export const BookingForm: React.FC<BookingFormProps> = ({
  initialData = {},
  onSubmit,
  loading = false
}) => {
  // ❌ REMOVE: const { toast } = useToast(); // Delete this line
  const [guestInfo, setGuestInfo] = useState<GuestInfo>({
    name: initialData.name || '',
    email: initialData.email || '',
    phone: initialData.phone || '',
  });
  const [specialRequests, setSpecialRequests] = useState('');
  const [paymentMethod, setPaymentMethod] = useState('upi');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    // Basic validation
    if (!guestInfo.name.trim() || !guestInfo.email.trim() || !guestInfo.phone.trim()) {
      // ✅ NEW: Use sonner's toast function
      toast.error("Please fill in all required fields");
      return;
    }

    if (!/^\d{10}$/.test(guestInfo.phone)) {
      // ✅ NEW: Use sonner's toast function
      toast.error("Please enter a valid 10-digit phone number");
      return;
    }

    onSubmit({
      ...guestInfo,
      specialRequests,
      paymentMethod
    });
  };

  return (
    <Card className="booking-form-card">
      <CardHeader>
        <CardTitle className="booking-form-title">Guest Information</CardTitle>
        <p className="booking-form-subtitle">Please provide your details for booking confirmation</p>
      </CardHeader>
      
      <CardContent>
        <form onSubmit={handleSubmit} className="booking-form">
          {/* Personal Information */}
          <div className="form-section">
            <h3 className="section-title">Personal Details</h3>
            <div className="input-grid">
              <div className="input-group">
                <Label htmlFor="name" className="input-label">
                  <User className="label-icon" />
                  Full Name *
                </Label>
                <Input
                  id="name"
                  placeholder="Enter your full name"
                  value={guestInfo.name}
                  onChange={(e) => setGuestInfo(prev => ({ ...prev, name: e.target.value }))}
                  className="booking-input"
                  required
                />
              </div>

              <div className="input-group">
                <Label htmlFor="email" className="input-label">
                  <Mail className="label-icon" />
                  Email Address *
                </Label>
                <Input
                  id="email"
                  type="email"
                  placeholder="your.email@example.com"
                  value={guestInfo.email}
                  onChange={(e) => setGuestInfo(prev => ({ ...prev, email: e.target.value }))}
                  className="booking-input"
                  required
                />
              </div>

              <div className="input-group">
                <Label htmlFor="phone" className="input-label">
                  <Phone className="label-icon" />
                  Phone Number *
                </Label>
                <Input
                  id="phone"
                  type="tel"
                  placeholder="9876543210"
                  value={guestInfo.phone}
                  onChange={(e) => setGuestInfo(prev => ({ ...prev, phone: e.target.value }))}
                  className="booking-input"
                  pattern="[0-9]{10}"
                  required
                />
              </div>
            </div>
          </div>

          {/* Special Requests */}
          <div className="form-section">
            <Label htmlFor="requests" className="input-label">
              <MessageSquare className="label-icon" />
              Special Requests (Optional)
            </Label>
            <Textarea
              id="requests"
              placeholder="Any special requirements, dietary restrictions, or preferences..."
              value={specialRequests}
              onChange={(e) => setSpecialRequests(e.target.value)}
              className="booking-textarea"
              rows={4}
            />
          </div>

          {/* Payment Method */}
          <div className="form-section">
            <h3 className="section-title">Payment Method</h3>
            <RadioGroup value={paymentMethod} onValueChange={setPaymentMethod} className="payment-methods">
              <div className="payment-option">
                <RadioGroupItem value="upi" id="upi" className="payment-radio" />
                <Label htmlFor="upi" className="payment-label">
                  <Wallet className="payment-icon" />
                  <div>
                    <span className="payment-name">UPI Payment</span>
                    <span className="payment-desc">Pay using UPI apps</span>
                  </div>
                </Label>
              </div>

              <div className="payment-option">
                <RadioGroupItem value="card" id="card" className="payment-radio" />
                <Label htmlFor="card" className="payment-label">
                  <CreditCard className="payment-icon" />
                  <div>
                    <span className="payment-name">Credit/Debit Card</span>
                    <span className="payment-desc">Pay with card</span>
                  </div>
                </Label>
              </div>

              <div className="payment-option">
                <RadioGroupItem value="cash" id="cash" className="payment-radio" />
                <Label htmlFor="cash" className="payment-label">
                  <Banknote className="payment-icon" />
                  <div>
                    <span className="payment-name">Pay at PG</span>
                    <span className="payment-desc">Pay in cash when you arrive</span>
                  </div>
                </Label>
              </div>
            </RadioGroup>
          </div>

          {/* Submit Button */}
          <div className="form-actions">
            <Button
              type="submit"
              className="submit-button"
              size="lg"
              disabled={loading}
            >
              {loading ? 'Processing...' : 'Confirm Booking'}
            </Button>
            <p className="booking-note">
              By confirming, you agree to our Terms of Service and Privacy Policy
            </p>
          </div>
        </form>
      </CardContent>
    </Card>
  );
};