// src/shared/components/layout/Footer.tsx
import { Button } from '@/components/ui/button';
import { ChevronRight, Sparkles } from 'lucide-react';
import { useEffect, useRef, useState } from 'react';
import { Link } from 'react-router-dom';

export const Footer = () => {
  const currentYear = new Date().getFullYear();
  const [isVisible, setIsVisible] = useState(false);
  const [showBrandSignature, setShowBrandSignature] = useState(false);
  const [textRevealed, setTextRevealed] = useState(false);
  const footerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    setIsVisible(true);

    const handleScroll = () => {
      if (footerRef.current) {
        const rect = footerRef.current.getBoundingClientRect();
        const windowHeight = window.innerHeight;
        
        // Show brand signature when footer is completely visible
        const shouldShow = rect.bottom <= windowHeight;
        setShowBrandSignature(shouldShow);
        
        // Trigger text reveal when brand signature is shown
        if (shouldShow && !textRevealed) {
          setTimeout(() => setTextRevealed(true), 300);
        } else if (!shouldShow) {
          setTextRevealed(false);
        }
      }
    };

    window.addEventListener('scroll', handleScroll, { passive: true });
    handleScroll(); // Check on initial load
    
    return () => window.removeEventListener('scroll', handleScroll);
  }, [textRevealed]);

  return (
    <>
      {/* Main Footer */}
      <footer className="relative bg-gradient-to-b from-white to-gray-50 text-gray-800 overflow-hidden pt-1" ref={footerRef}>
        <div className={`fixed bottom-6 right-6 z-50 transition-all duration-500 delay-1000 ${
          isVisible ? 'opacity-100 scale-100' : 'opacity-0 scale-50'
        }`}>
          <Button className="group rounded-full bg-gradient-to-r from-blue-600 to-purple-600 text-white shadow-2xl hover:shadow-3xl px-6 py-6 hover:scale-105 transition-all duration-300">
            <div className="flex items-center gap-2">
              <Sparkles className="h-5 w-5 group-hover:rotate-12 transition-transform" />
              <span className="font-semibold">Need Help?</span>
            </div>
          </Button>
        </div>
      </footer>

      {/* Brand Signature - Separate section below footer */}
      <div 
        className={`relative transition-all duration-1000 ${
          showBrandSignature 
            ? 'opacity-100 translate-y-0' 
            : 'opacity-0 -translate-y-10 pointer-events-none'
        }`}
        style={{
          background: 'linear-gradient(135deg, #0f172a 0%, #1e293b 30%, #312e81 60%, #4c1d95 100%)'
        }}
      >
        {/* Background elements matching home screen */}
        <div className="absolute inset-0 overflow-hidden pointer-events-none">
          {/* Gradient orbs like home screen */}
          <div className="absolute top-1/4 left-1/4 w-96 h-96 bg-blue-500/10 rounded-full blur-3xl animate-pulse" />
          <div className="absolute bottom-1/4 right-1/4 w-96 h-96 bg-purple-500/10 rounded-full blur-3xl animate-pulse delay-1000" />
          
          {/* Grid pattern like home screen */}
          <div className="absolute inset-0 opacity-5" style={{
            backgroundImage: `linear-gradient(rgba(255,255,255,0.1) 1px, transparent 1px),
                            linear-gradient(90deg, rgba(255,255,255,0.1) 1px, transparent 1px)`,
            backgroundSize: '50px 50px'
          }} />
          
          {/* Top gradient line */}
          <div className="absolute top-0 left-0 right-0 h-px bg-gradient-to-r from-transparent via-blue-400 to-transparent opacity-30" />
        </div>

        {/* Content */}
        <div className="relative z-10 container mx-auto px-4 py-20">
          <div className="max-w-6xl mx-auto">
            {/* Brand name in large text with left-to-right animation AND glowing gradient */}
            <div className={`mb-12 transition-all duration-1000 delay-300 ${
              showBrandSignature ? 'opacity-100 scale-100' : 'opacity-0 scale-95'
            }`}>
              <div className="overflow-hidden">
                <h2 className="text-6xl md:text-8xl lg:text-9xl font-black text-center tracking-tighter">
                  <span className="relative block w-full">
                    <span 
                      className="inline-block whitespace-nowrap animate-gradient"
                      style={{
                        transform: textRevealed ? 'translateX(0)' : 'translateX(-100vw)',
                        transition: 'transform 1.2s cubic-bezier(0.68, -0.55, 0.265, 1.55)',
                        transitionDelay: '0.3s'
                      }}
                    >
                      FindPGNearby
                    </span>
                  </span>
                </h2>
              </div>
            </div>

            {/* Tagline */}
            <div className={`mb-16 transition-all duration-1000 delay-500 ${
              showBrandSignature ? 'opacity-100 translate-y-0' : 'opacity-0 translate-y-5'
            }`}>
              <p className="text-2xl md:text-3xl text-center text-gray-200 font-medium tracking-wide mb-6">
                Your Journey Home Begins Here
              </p>
              <p className="text-lg text-center text-gray-400 max-w-3xl mx-auto">
                From search to move-in, we're with you every step of the way. 
                Join India's fastest growing community of students and professionals 
                finding their perfect home away from home.
              </p>
            </div>

            {/* Stats grid */}
            <div className={`grid grid-cols-2 md:grid-cols-4 gap-8 mb-16 transition-all duration-1000 delay-700 ${
              showBrandSignature ? 'opacity-100 translate-y-0' : 'opacity-0 translate-y-10'
            }`}>
              {[
                { value: '75+', label: 'Cities Across India', color: 'text-blue-300' },
                { value: '15K+', label: 'Verified PGs', color: 'text-purple-300' },
                { value: '50K+', label: 'Happy Residents', color: 'text-pink-300' },
                { value: '4.9★', label: 'Customer Rating', color: 'text-amber-300' },
              ].map((stat, index) => (
                <div key={index} className="text-center">
                  <div className={`text-4xl md:text-5xl font-bold ${stat.color} mb-3`}>{stat.value}</div>
                  <div className="text-gray-400 text-sm">{stat.label}</div>
                </div>
              ))}
            </div>

            {/* Call to action */}
            <div className={`text-center transition-all duration-1000 delay-900 ${
              showBrandSignature ? 'opacity-100 translate-y-0' : 'opacity-0 translate-y-5'
            }`}>
              <Button 
                size="lg" 
                className="bg-gradient-to-r from-blue-600 to-purple-600 hover:from-blue-700 hover:to-purple-700 text-white px-12 py-7 text-xl font-bold rounded-2xl shadow-2xl hover:shadow-3xl transition-all hover:scale-105"
                asChild
              >
                <Link to="/search">
                  <span>Start Your Search Today</span>
                  <ChevronRight className="ml-3 h-6 w-6" />
                </Link>
              </Button>
            </div>

            {/* Footer links */}
            <div className={`mt-20 pt-12 border-t border-gray-800 transition-all duration-1000 delay-1000 ${
              showBrandSignature ? 'opacity-100 translate-y-0' : 'opacity-0 translate-y-5'
            }`}>
              <div className="flex flex-col md:flex-row items-center justify-between gap-6">
                <div className="text-gray-500 text-sm">
                  &copy; {currentYear} FindPGNearby Technologies Pvt Ltd.
                </div>
                
                <div className="flex items-center gap-8">
                  {['Privacy Policy', 'Terms of Service', 'Cookie Policy', 'Contact'].map((item, index) => (
                    <Link 
                      key={item}
                      to="#" 
                      className="text-gray-400 hover:text-white transition-colors text-sm hover:underline underline-offset-4"
                    >
                      {item}
                    </Link>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Scroll indicator */}
        {showBrandSignature && (
          <div className="absolute bottom-8 left-1/2 -translate-x-1/2 animate-bounce">
            <div className="flex flex-col items-center gap-2 text-gray-400 text-sm">
              <span>Scroll up</span>
              <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 10l7-7m0 0l7 7m-7-7v18" />
              </svg>
            </div>
          </div>
        )}
      </div>

      {/* Animation keyframes */}
      <style>{`
        @keyframes gradient {
          0%, 100% {
            background-position: 0% 50%;
          }
          50% {
            background-position: 100% 50%;
          }
        }
        
        .animate-gradient {
          background: linear-gradient(
            90deg,
            #3b82f6,
            #8b5cf6,
            #ec4899,
            #8b5cf6,
            #3b82f6
          );
          background-size: 300% 300%;
          animation: gradient 3s ease infinite;
          -webkit-background-clip: text;
          background-clip: text;
          color: transparent;
        }
        
        @keyframes fadeInUp {
          from {
            opacity: 0;
            transform: translateY(20px);
          }
          to {
            opacity: 1;
            transform: translateY(0);
          }
        }
        
        .animate-fade-in-up {
          animation: fadeInUp 0.6s ease-out forwards;
        }
        
        /* Smooth scroll behavior */
        html {
          scroll-behavior: smooth;
        }
      `}</style>
    </>
  );
};