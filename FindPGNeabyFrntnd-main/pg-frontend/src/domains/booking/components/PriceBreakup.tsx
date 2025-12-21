import { IndianRupee, Percent, Shield, Tag } from 'lucide-react';
import React from 'react';

import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Separator } from '@/components/ui/separator';
import '../css/PriceBreakup.css';

interface PriceBreakupProps {
  pricePerNight: number;
  nights: number;
  guests: number;
  serviceFee?: number;
  discount?: number;
  taxes?: number;
}

export const PriceBreakup: React.FC<PriceBreakupProps> = ({
  pricePerNight,
  nights,
  guests = 1,
  serviceFee = 99,
  discount = 0,
  taxes = 18
}) => {
  const subtotal = pricePerNight * nights;
  const taxAmount = (subtotal * taxes) / 100;
  const total = subtotal + serviceFee + taxAmount - discount;

  return (
    <Card className="price-breakup-card">
      <CardHeader>
        <CardTitle className="price-breakup-title">
          <IndianRupee className="title-icon" />
          Price Breakdown
        </CardTitle>
      </CardHeader>
      <CardContent>
        <div className="price-details">
          {/* Price per night */}
          <div className="price-row">
            <span className="price-label">
              ₹{pricePerNight.toLocaleString()} × {nights} nights
              {guests > 1 && ` × ${guests} guests`}
            </span>
            <span className="price-value">₹{subtotal.toLocaleString()}</span>
          </div>

          {/* Service fee */}
          {serviceFee > 0 && (
            <div className="price-row">
              <span className="price-label">
                <Shield className="inline-icon" />
                Service fee
              </span>
              <span className="price-value">₹{serviceFee.toLocaleString()}</span>
            </div>
          )}

          {/* Taxes */}
          <div className="price-row">
            <span className="price-label">
              Taxes & charges ({taxes}%)
            </span>
            <span className="price-value">₹{taxAmount.toLocaleString()}</span>
          </div>

          {/* Discount */}
          {discount > 0 && (
            <div className="price-row discount-row">
              <span className="price-label">
                <Tag className="inline-icon" />
                Discount
              </span>
              <span className="price-value discount">-₹{discount.toLocaleString()}</span>
            </div>
          )}

          <Separator className="my-4" />

          {/* Total */}
          <div className="price-row total-row">
            <span className="total-label">Total</span>
            <div className="total-amount">
              <span className="total-currency">₹</span>
              <span className="total-value">{total.toLocaleString()}</span>
            </div>
          </div>

          {/* Per night cost */}
          <div className="per-night-cost">
            <span className="per-night-label">
              <Percent className="inline-icon" />
              Per night cost
            </span>
            <span className="per-night-value">
              ₹{(total / nights).toLocaleString('en-IN', { maximumFractionDigits: 2 })}
            </span>
          </div>
        </div>
      </CardContent>
    </Card>
  );
};