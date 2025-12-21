import { Button } from '@/components/ui/button';
import { Calendar } from '@/components/ui/calendar';
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover';
import { cn } from '@/lib/utils';
import { format } from 'date-fns';
import { CalendarIcon } from 'lucide-react';
import React, { useState } from 'react';
import '../css/DateSelector.css';

interface DateSelectorProps {
  checkIn: Date | undefined;
  checkOut: Date | undefined;
  onCheckInChange: (date: Date | undefined) => void;
  onCheckOutChange: (date: Date | undefined) => void;
  minDate?: Date;
  maxDate?: Date;
  className?: string;
}

export const DateSelector: React.FC<DateSelectorProps> = ({
  checkIn,
  checkOut,
  onCheckInChange,
  onCheckOutChange,
  minDate = new Date(),
  maxDate,
  className
}) => {
  const [openCheckIn, setOpenCheckIn] = useState(false);
  const [openCheckOut, setOpenCheckOut] = useState(false);

  return (
    <div className={`date-selector-container ${className}`}>
      <div className="date-input-group">
        <div className="date-input-wrapper">
          <label className="date-label">Check-in</label>
          <Popover open={openCheckIn} onOpenChange={setOpenCheckIn}>
            <PopoverTrigger asChild>
              <Button
                variant="outline"
                className={cn(
                  "w-full justify-start text-left font-normal date-input",
                  !checkIn && "text-muted-foreground"
                )}
              >
                <CalendarIcon className="mr-2 h-4 w-4" />
                {checkIn ? format(checkIn, "PPP") : "Select date"}
              </Button>
            </PopoverTrigger>
            <PopoverContent className="w-auto p-0" align="start">
              <Calendar
                mode="single"
                selected={checkIn}
                onSelect={(date) => {
                  onCheckInChange(date);
                  setOpenCheckIn(false);
                }}
                disabled={(date) => 
                  date < minDate || 
                  (maxDate ? date > maxDate : false) // ✅ Always returns boolean
                }
                initialFocus
              />
            </PopoverContent>
          </Popover>
        </div>

        <div className="date-input-wrapper">
          <label className="date-label">Check-out</label>
          <Popover open={openCheckOut} onOpenChange={setOpenCheckOut}>
            <PopoverTrigger asChild>
              <Button
                variant="outline"
                className={cn(
                  "w-full justify-start text-left font-normal date-input",
                  !checkOut && "text-muted-foreground"
                )}
                disabled={!checkIn}
              >
                <CalendarIcon className="mr-2 h-4 w-4" />
                {checkOut ? format(checkOut, "PPP") : "Select date"}
              </Button>
            </PopoverTrigger>
            <PopoverContent className="w-auto p-0" align="start">
              <Calendar
                mode="single"
                selected={checkOut}
                onSelect={(date) => {
                  onCheckOutChange(date);
                  setOpenCheckOut(false);
                }}
                disabled={(date) => 
                  date <= (checkIn || minDate) || 
                  (maxDate ? date > maxDate : false) // ✅ Always returns boolean
                }
                initialFocus
              />
            </PopoverContent>
          </Popover>
        </div>
      </div>

      {checkIn && checkOut && (
        <div className="date-summary">
          <span className="nights-count">
            {Math.ceil((checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24))} nights
          </span>
          <span className="date-range">
            {format(checkIn, "MMM dd")} - {format(checkOut, "MMM dd, yyyy")}
          </span>
        </div>
      )}
    </div>
  );
};