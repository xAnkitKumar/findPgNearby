// src/shared/components/layout/Navbar.tsx
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { Badge } from '@/components/ui/badge';
import { Button } from '@/components/ui/button';
import {
    DropdownMenu,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuLabel,
    DropdownMenuSeparator,
    DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu';
import { cn } from '@/lib/utils';
import { Bell, Building, Calendar, ChevronDown, Home, Menu, Search, Sparkles, User, X } from 'lucide-react';
import { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';

export const Navbar = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [scrolled, setScrolled] = useState(false);
  const [isAtTop, setIsAtTop] = useState(true);
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const handleScroll = () => {
      const scrollY = window.scrollY;
      setScrolled(scrollY > 10);
      setIsAtTop(scrollY < 5);
    };
    window.addEventListener('scroll', handleScroll);
    handleScroll(); // Initial check
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  const navLinks = [
    { name: 'Home', path: '/', icon: Home },
    { name: 'Search PGs', path: '/search', icon: Search },
    { name: 'My Bookings', path: '/my-bookings', icon: Calendar, badge: 3 },
    { name: 'List Your PG', path: '/list-pg', icon: Building, featured: true },
  ];

  const isActive = (path: string) => location.pathname === path;

  return (
    <>
      {/* Enhanced Navbar with Glassmorphism */}
      <nav className={cn(
        "fixed top-0 left-0 right-0 z-50 w-full transition-all duration-500 ease-out",
        scrolled 
          ? "bg-white/80 backdrop-blur-xl border-b border-white/20 shadow-lg shadow-black/5"
          : "bg-gradient-to-b from-black/20 via-black/10 to-transparent backdrop-blur-md"
      )}>
        {/* Decorative Elements */}
        <div className="absolute inset-0 overflow-hidden pointer-events-none">
          {/* Animated Gradient Lines */}
          <div className={cn(
            "absolute top-0 left-0 right-0 h-[1px] bg-gradient-to-r from-transparent via-white/30 to-transparent transition-opacity duration-500",
            scrolled ? "opacity-0" : "opacity-100"
          )} />
          
          {/* Floating Particles */}
          {!scrolled && (
            <>
              <div className="absolute top-4 left-1/4 w-1 h-1 bg-blue-400/30 rounded-full animate-pulse" />
              <div className="absolute top-3 right-1/3 w-1 h-1 bg-purple-400/30 rounded-full animate-pulse delay-300" />
              <div className="absolute top-6 left-2/3 w-1 h-1 bg-pink-400/30 rounded-full animate-pulse delay-700" />
            </>
          )}
        </div>

        <div className="container mx-auto px-4 lg:px-8">
          <div className="flex h-16 lg:h-20 items-center justify-between">
            
            {/* Enhanced Logo with Animation */}
            <div className="flex items-center gap-3">
              <Link to="/" className="flex items-center gap-3 group">
                <div className="relative">
                  <div className={cn(
                    "h-10 w-10 rounded-xl flex items-center justify-center shadow-lg transition-all duration-300 group-hover:scale-110",
                    scrolled 
                      ? "bg-gradient-to-br from-blue-600 via-purple-600 to-pink-600"
                      : "bg-gradient-to-br from-white/20 to-white/10 backdrop-blur-sm border border-white/20"
                  )}>
                    <Home className={cn(
                      "h-5 w-5 transition-all duration-300",
                      scrolled ? "text-white" : "text-white/90"
                    )} />
                  </div>
                  <div className={cn(
                    "absolute -top-1 -right-1 h-4 w-4 rounded-full border-2 transition-all duration-300",
                    scrolled 
                      ? "bg-green-500 border-white"
                      : "bg-green-400/90 border-white/20"
                  )} />
                </div>
                <div className="flex flex-col">
                  <span className={cn(
                    "text-xl font-bold transition-all duration-300",
                    scrolled 
                      ? "bg-gradient-to-r from-blue-600 to-purple-600 bg-clip-text text-transparent"
                      : "text-white drop-shadow-lg"
                  )}>
                    FindPGnearby
                  </span>
                  <span className={cn(
                    "text-xs transition-all duration-300",
                    scrolled ? "text-gray-500" : "text-white/70"
                  )}>
                    Your Trusted PG Partner
                  </span>
                </div>
              </Link>
            </div>

            {/* Desktop Navigation with Enhanced Effects */}
            <div className="hidden lg:flex items-center gap-1">
              {navLinks.map((link) => {
                const Icon = link.icon;
                return (
                  <Button
                    key={link.name}
                    variant="ghost"
                    size="sm"
                    className={cn(
                      'gap-2 px-4 py-2 rounded-xl transition-all duration-300 relative overflow-hidden group',
                      'hover:scale-[1.02] active:scale-95',
                      isActive(link.path) 
                        ? scrolled
                          ? 'bg-gradient-to-r from-blue-500/10 to-purple-500/10 text-blue-700'
                          : 'bg-white/20 backdrop-blur-sm text-white border border-white/30'
                        : scrolled
                          ? 'hover:bg-gray-50/80 text-gray-700 hover:text-gray-900'
                          : 'text-white/90 hover:text-white hover:bg-white/10'
                    )}
                    asChild
                  >
                    <Link to={link.path} className="flex items-center gap-2">
                      {/* Hover Effect Line */}
                      <span className={cn(
                        "absolute bottom-0 left-1/2 h-[2px] w-8 -translate-x-1/2 bg-gradient-to-r from-blue-400 to-purple-400 rounded-full transition-all duration-300",
                        isActive(link.path) ? "opacity-100" : "opacity-0 group-hover:opacity-100"
                      )} />
                      
                      <Icon className="h-4 w-4 relative z-10" />
                      <span className="relative z-10 font-medium">{link.name}</span>
                      
                      {link.badge && (
                        <Badge className={cn(
                          "ml-1 h-5 w-5 flex items-center justify-center p-0 relative z-10 transition-all duration-300",
                          scrolled 
                            ? "bg-red-500 hover:bg-red-600" 
                            : "bg-red-400/90 hover:bg-red-500/90"
                        )}>
                          {link.badge}
                        </Badge>
                      )}
                      
                      {link.featured && (
                        <Sparkles className={cn(
                          "h-3 w-3 ml-1 relative z-10 transition-all duration-300",
                          scrolled ? "text-yellow-500" : "text-yellow-300"
                        )} />
                      )}
                    </Link>
                  </Button>
                );
              })}
            </div>

            {/* Enhanced User Actions */}
            <div className="hidden lg:flex items-center gap-2">
              <Button 
                variant="ghost" 
                size="icon" 
                className={cn(
                  "relative rounded-full transition-all duration-300 hover:scale-110",
                  scrolled 
                    ? "text-gray-600 hover:text-gray-900 hover:bg-gray-100/80"
                    : "text-white/90 hover:text-white hover:bg-white/10"
                )}
              >
                <Bell className="h-5 w-5" />
                <span className={cn(
                  "absolute -top-1 -right-1 h-5 w-5 rounded-full text-xs flex items-center justify-center transition-all duration-300",
                  scrolled 
                    ? "bg-red-500 text-white" 
                    : "bg-red-400/90 text-white backdrop-blur-sm"
                )}>
                  2
                </span>
              </Button>
              
              <DropdownMenu>
                <DropdownMenuTrigger asChild>
                  <Button 
                    variant="ghost" 
                    className={cn(
                      "gap-2 px-3 py-2 rounded-full transition-all duration-300 group hover:scale-[1.02]",
                      scrolled 
                        ? "hover:bg-gray-100/80" 
                        : "hover:bg-white/10"
                    )}
                  >
                    <Avatar className={cn(
                      "h-8 w-8 transition-all duration-300 group-hover:scale-110",
                      scrolled 
                        ? "border-2 border-blue-100" 
                        : "border-2 border-white/30"
                    )}>
                      <AvatarImage src="https://api.dicebear.com/7.x/avataaars/svg?seed=user" />
                      <AvatarFallback className={cn(
                        "transition-all duration-300",
                        scrolled 
                          ? "bg-gradient-to-br from-blue-100 to-purple-100 text-blue-700"
                          : "bg-white/20 text-white/90"
                      )}>
                        JD
                      </AvatarFallback>
                    </Avatar>
                    <div className="flex flex-col items-start">
                      <span className={cn(
                        "text-sm font-medium transition-all duration-300",
                        scrolled ? "text-gray-900" : "text-white"
                      )}>
                        John Doe
                      </span>
                      <span className={cn(
                        "text-xs transition-all duration-300",
                        scrolled ? "text-gray-500" : "text-white/70"
                      )}>
                        Premium Member
                      </span>
                    </div>
                    <ChevronDown className={cn(
                      "h-4 w-4 transition-all duration-300",
                      scrolled ? "text-gray-500" : "text-white/70"
                    )} />
                  </Button>
                </DropdownMenuTrigger>
                <DropdownMenuContent 
                  className="w-56 bg-white/95 backdrop-blur-xl border border-gray-200/50 shadow-2xl" 
                  align="end"
                >
                  <DropdownMenuLabel className="flex items-center gap-2">
                    <div className="h-8 w-8 rounded-full bg-gradient-to-br from-blue-500 to-purple-500 flex items-center justify-center">
                      <User className="h-4 w-4 text-white" />
                    </div>
                    <div>
                      <p className="font-semibold">John Doe</p>
                      <p className="text-xs text-gray-500">Premium Member</p>
                    </div>
                  </DropdownMenuLabel>
                  <DropdownMenuSeparator />
                  <DropdownMenuItem asChild className="cursor-pointer hover:bg-gray-50">
                    <Link to="/profile" className="w-full">Profile</Link>
                  </DropdownMenuItem>
                  <DropdownMenuItem asChild className="cursor-pointer hover:bg-gray-50">
                    <Link to="/my-bookings" className="w-full">My Bookings</Link>
                  </DropdownMenuItem>
                  <DropdownMenuItem asChild className="cursor-pointer hover:bg-gray-50">
                    <Link to="/settings" className="w-full">Settings</Link>
                  </DropdownMenuItem>
                  <DropdownMenuSeparator />
                  <DropdownMenuItem className="text-red-600 focus:text-red-600 cursor-pointer hover:bg-red-50">
                    Logout
                  </DropdownMenuItem>
                </DropdownMenuContent>
              </DropdownMenu>
            </div>

            {/* Enhanced Mobile Menu Button */}
            <Button
              variant="ghost"
              size="icon"
              className="lg:hidden rounded-full transition-all duration-300 hover:scale-110"
              onClick={() => setIsMenuOpen(!isMenuOpen)}
            >
              {isMenuOpen ? (
                <X className={cn(
                  "h-5 w-5 transition-all duration-300",
                  scrolled ? "text-gray-700" : "text-white"
                )} />
              ) : (
                <Menu className={cn(
                  "h-5 w-5 transition-all duration-300",
                  scrolled ? "text-gray-700" : "text-white"
                )} />
              )}
            </Button>
          </div>
        </div>
      </nav>

      {/* Enhanced Mobile Menu */}
      {isMenuOpen && (
        <div className="lg:hidden fixed inset-0 z-40 pt-16">
          {/* Backdrop */}
          <div 
            className="absolute inset-0 bg-black/50 backdrop-blur-sm"
            onClick={() => setIsMenuOpen(false)}
          />
          
          {/* Menu Panel */}
          <div className="relative ml-auto w-full max-w-sm h-full bg-gradient-to-b from-white to-gray-50 shadow-2xl animate-slide-in">
            <div className="p-6 space-y-2">
              {/* User Profile Card */}
              <div className="mb-6 p-4 rounded-2xl bg-gradient-to-r from-blue-50 to-purple-50 border border-blue-100">
                <div className="flex items-center gap-3">
                  <Avatar className="h-12 w-12 border-3 border-white shadow-lg">
                    <AvatarImage src="https://api.dicebear.com/7.x/avataaars/svg?seed=user" />
                    <AvatarFallback className="bg-gradient-to-br from-blue-500 to-purple-500 text-white">
                      JD
                    </AvatarFallback>
                  </Avatar>
                  <div className="flex-1">
                    <p className="font-semibold text-gray-900">John Doe</p>
                    <div className="flex items-center gap-2 mt-1">
                      <Badge className="bg-gradient-to-r from-amber-500 to-orange-500 text-white border-0 text-xs">
                        <Sparkles className="h-3 w-3 mr-1" />
                        Premium
                      </Badge>
                      <span className="text-xs text-gray-500">Member</span>
                    </div>
                  </div>
                </div>
              </div>

              {/* Navigation Links */}
              {navLinks.map((link) => {
                const Icon = link.icon;
                return (
                  <Button
                    key={link.name}
                    variant={isActive(link.path) ? 'secondary' : 'ghost'}
                    className={cn(
                      "w-full justify-start gap-3 px-4 py-3 rounded-xl transition-all duration-300",
                      isActive(link.path) && "bg-gradient-to-r from-blue-50 to-purple-50 border border-blue-100"
                    )}
                    asChild
                  >
                    <Link to={link.path} onClick={() => setIsMenuOpen(false)}>
                      <Icon className="h-4 w-4" />
                      <span className="font-medium">{link.name}</span>
                      {link.badge && (
                        <Badge className="ml-auto bg-red-500 hover:bg-red-600">
                          {link.badge}
                        </Badge>
                      )}
                      {link.featured && (
                        <Sparkles className="ml-auto h-4 w-4 text-yellow-500" />
                      )}
                    </Link>
                  </Button>
                );
              })}
              
              <div className="pt-6 mt-6 border-t border-gray-200 space-y-3">
                <Button variant="outline" className="w-full justify-start gap-3" asChild>
                  <Link to="/profile" onClick={() => setIsMenuOpen(false)}>
                    <User className="h-4 w-4" />
                    My Profile
                  </Link>
                </Button>
                
                <Button 
                  className="w-full gap-3 bg-gradient-to-r from-blue-600 to-purple-600 hover:from-blue-700 hover:to-purple-700 shadow-lg hover:shadow-xl transition-all"
                  asChild
                >
                  <Link to="/premium" onClick={() => setIsMenuOpen(false)}>
                    <Sparkles className="h-4 w-4" />
                    Upgrade to Premium
                  </Link>
                </Button>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* Add slide-in animation to global styles or separate CSS */}
      <style>{`
        @keyframes slide-in {
          from {
            transform: translateX(100%);
          }
          to {
            transform: translateX(0);
          }
        }
        
        .animate-slide-in {
          animation: slide-in 0.3s ease-out;
        }
      `}</style>
    </>
  );
};