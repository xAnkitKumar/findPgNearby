// src/App.tsx
import { Toaster } from '@/components/ui/sonner';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { BookingPage } from './domains/booking/pages/Booking';
import { HomePage } from './pages/Home';
import { Navbar } from './shared/components/layout/Navbar';

import './App.css';
import { Footer } from './shared/components/layout/Footer';

function App() {
  return (
    <BrowserRouter>
      <div className="min-h-screen bg-gray-50 flex flex-col">
        <Navbar />
        <main className="flex-grow">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/booking" element={<BookingPage />} />
           
          </Routes>
        </main>
        <Footer />
        <Toaster />
      </div>
    </BrowserRouter>
  );
}

export default App;