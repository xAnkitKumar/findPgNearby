// src/pages/Home.tsx
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { Badge } from '@/components/ui/badge';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';
import { Input } from '@/components/ui/input';
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
} from "@/components/ui/select";
import {
    ArrowRight,
    Calendar,
    Car,
    ChevronDown,
    Coffee,
    Dumbbell,
    Globe,
    Heart,
    Home as HomeIcon,
    Lock,
    MapPin,
    Medal,
    Search,
    ShieldCheck,
    Smartphone,
    Sparkles,
    Star,
    ThumbsUp,
    Tv,
    Wifi,
    Zap
} from 'lucide-react';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import '../css/Home.css';

export const HomePage = () => {
  const [activeCity, setActiveCity] = useState('all');

  const cities = [
    { name: 'Bangalore', properties: '2,500+', image: 'https://images.unsplash.com/photo-1592210454359-9043f067919b?q=80&w=2070', color: 'from-blue-500 to-cyan-500' },
    { name: 'Delhi', properties: '1,800+', image: 'https://images.unsplash.com/photo-1587474260584-136574528ed5?q=80&w=2070', color: 'from-orange-500 to-red-500' },
    { name: 'Mumbai', properties: '1,600+', image: 'https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?q=80&w=2070', color: 'from-purple-500 to-pink-500' },
    { name: 'Hyderabad', properties: '1,200+', image: 'https://images.unsplash.com/photo-1596176530529-78163a4f7af2?q=80&w=2070', color: 'from-green-500 to-teal-500' },
    { name: 'Chennai', properties: '900+', image: 'https://images.unsplash.com/photo-1595665593673-bf1ad72905c0?q=80&w=2070', color: 'from-yellow-500 to-amber-500' },
    { name: 'Pune', properties: '1,100+', image: 'https://images.unsplash.com/photo-1529254479751-fbacb4c7a587?q=80&w=2070', color: 'from-indigo-500 to-blue-500' },
  ];

  const testimonials = [
    {
      name: 'Rajesh Kumar',
      role: 'Software Engineer, Google',
      content: 'Found my perfect PG in Bangalore within 2 days! The verification process gave me confidence.',
      rating: 5,
      avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Rajesh',
      location: 'Bangalore'
    },
    {
      name: 'Priya Sharma',
      role: 'Medical Student',
      content: 'As a student, safety was my priority. FindPGnearby verified everything before booking.',
      rating: 5,
      avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Priya',
      location: 'Delhi'
    },
    {
      name: 'Amit Patel',
      role: 'Business Analyst, Amazon',
      content: '24/7 support is amazing. They helped me reschedule when my plans changed suddenly.',
      rating: 4,
      avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Amit',
      location: 'Mumbai'
    },
  ];

  const features = [
    {
      icon: ShieldCheck,
      title: 'Verified Properties',
      description: 'Every PG undergoes 10-point verification',
      color: 'bg-blue-100 text-blue-600'
    },
    {
      icon: Lock,
      title: 'Secure Booking',
      description: 'Your payment is protected until you move in',
      color: 'bg-green-100 text-green-600'
    },
    {
      icon: Smartphone,
      title: 'Digital Agreement',
      description: 'Sign contracts online, no paperwork needed',
      color: 'bg-purple-100 text-purple-600'
    },
    {
      icon: ThumbsUp,
      title: 'Customer Reviews',
      description: 'Real feedback from verified residents',
      color: 'bg-amber-100 text-amber-600'
    },
  ];

  return (
    <div className="home-page min-h-screen">
      {/* Hero Section - Full Screen */}
      <section className="relative min-h-screen flex items-center justify-center overflow-hidden">
        {/* Background with gradient overlay */}
        <div className="absolute inset-0 bg-gradient-to-br from-blue-900 via-purple-900 to-gray-900">
          <div className="absolute inset-0 bg-[url('https://images.unsplash.com/photo-1566073771259-6a8506099945?q=80&w=2070')] bg-cover bg-center opacity-30" />
          <div className="absolute inset-0 bg-gradient-to-t from-black/70 via-black/40 to-transparent" />
        </div>

        {/* Floating Elements */}
        <div className="absolute top-20 left-10 w-72 h-72 bg-blue-500/10 rounded-full blur-3xl" />
        <div className="absolute bottom-20 right-10 w-96 h-96 bg-purple-500/10 rounded-full blur-3xl" />

        <div className="relative z-10 container mx-auto px-4 lg:px-8">
          <div className="max-w-6xl mx-auto">
            {/* Trust Badge */}
            <div className="flex justify-center mb-8">
              <Badge className="bg-white/20 backdrop-blur-md text-white border-white/30 px-6 py-2 rounded-full">
                <Sparkles className="h-4 w-4 mr-2" />
                Trusted by 50,000+ professionals & students
              </Badge>
            </div>

            {/* Main Heading */}
            <h1 className="text-5xl lg:text-7xl font-bold text-center text-white mb-6 leading-tight">
              Find Your Perfect
              <span className="block bg-gradient-to-r from-blue-400 via-purple-400 to-pink-400 bg-clip-text text-transparent animate-gradient">
                Home Away From Home
              </span>
            </h1>

            {/* Subtitle */}
            <p className="text-xl lg:text-2xl text-gray-200 text-center mb-12 max-w-3xl mx-auto leading-relaxed">
              Discover verified PG accommodations with premium amenities. 
              Book with confidence, live with comfort.
            </p>

            {/* Search Card - Airbnb Style */}
            <div className="bg-white rounded-2xl shadow-2xl p-6 lg:p-8 max-w-5xl mx-auto transform hover:scale-[1.01] transition-transform duration-300">
              <div className="grid grid-cols-1 lg:grid-cols-4 gap-4">
                {/* Location */}
                <div className="space-y-2">
                  <label className="text-sm font-semibold text-gray-700 flex items-center gap-2">
                    <MapPin className="h-4 w-4 text-blue-600" />
                    LOCATION
                  </label>
                  <Select>
                    <SelectTrigger className="h-14 border-2 border-gray-200 hover:border-blue-500 transition-colors">
                      <SelectValue placeholder="Where do you want to stay?" />
                    </SelectTrigger>
                    <SelectContent>
                      {cities.map(city => (
                        <SelectItem key={city.name} value={city.name.toLowerCase()}>
                          <div className="flex items-center gap-3">
                            <div className={`h-3 w-3 rounded-full bg-gradient-to-r ${city.color}`} />
                            {city.name}
                          </div>
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>

                {/* Check-in */}
                <div className="space-y-2">
                  <label className="text-sm font-semibold text-gray-700 flex items-center gap-2">
                    <Calendar className="h-4 w-4 text-blue-600" />
                    CHECK-IN
                  </label>
                  <Input 
                    className="h-14 border-2 border-gray-200 hover:border-blue-500 transition-colors"
                    placeholder="Add dates"
                    type="date"
                  />
                </div>

                {/* Check-out */}
                <div className="space-y-2">
                  <label className="text-sm font-semibold text-gray-700 flex items-center gap-2">
                    <Calendar className="h-4 w-4 text-blue-600" />
                    CHECK-OUT
                  </label>
                  <Input 
                    className="h-14 border-2 border-gray-200 hover:border-blue-500 transition-colors"
                    placeholder="Add dates"
                    type="date"
                  />
                </div>

                {/* Search Button */}
                <div className="flex items-end">
                  <Button className="w-full h-14 bg-gradient-to-r from-blue-600 to-purple-600 hover:from-blue-700 hover:to-purple-700 text-white rounded-xl shadow-lg hover:shadow-xl transition-all">
                    <Search className="mr-3 h-5 w-5" />
                    <span className="font-semibold">Search PGs</span>
                  </Button>
                </div>
              </div>

              {/* Quick Filters */}
              <div className="mt-6 pt-6 border-t border-gray-100">
                <div className="flex flex-wrap gap-3 justify-center">
                  {[
                    { label: 'WiFi Included', icon: Wifi },
                    { label: 'Food Available', icon: Coffee },
                    { label: 'Parking', icon: Car },
                    { label: 'Gym Access', icon: Dumbbell },
                    { label: 'TV in Room', icon: Tv },
                  ].map((item, index) => (
                    <Badge 
                      key={index}
                      variant="secondary"
                      className="gap-2 px-4 py-2 rounded-full hover:bg-gray-100 cursor-pointer transition-colors"
                    >
                      <item.icon className="h-4 w-4" />
                      {item.label}
                    </Badge>
                  ))}
                </div>
              </div>
            </div>

            {/* Scroll Indicator */}
            <div className="text-center mt-12">
              <div className="inline-flex flex-col items-center gap-2 text-white/70 animate-bounce">
                <span className="text-sm">Explore more</span>
                <ChevronDown className="h-6 w-6" />
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Stats Section */}
      <section className="py-16 bg-white">
        <div className="container mx-auto px-4 lg:px-8">
          <div className="grid grid-cols-2 lg:grid-cols-4 gap-6">
            {[
              { value: '15,000+', label: 'Verified PGs', icon: ShieldCheck, color: 'bg-blue-50 text-blue-600' },
              { value: '50,000+', label: 'Happy Residents', icon: Heart, color: 'bg-pink-50 text-pink-600' },
              { value: '75+', label: 'Cities Across India', icon: Globe, color: 'bg-green-50 text-green-600' },
              { value: '98.7%', label: 'Satisfaction Rate', icon: Medal, color: 'bg-amber-50 text-amber-600' },
            ].map((stat, index) => {
              const Icon = stat.icon;
              return (
                <Card key={index} className="border-0 shadow-lg hover:shadow-2xl transition-all duration-300 hover:-translate-y-2">
                  <CardContent className="p-8">
                    <div className={`inline-flex p-4 rounded-2xl ${stat.color} mb-6`}>
                      <Icon className="h-8 w-8" />
                    </div>
                    <h3 className="text-4xl font-bold text-gray-900 mb-2">{stat.value}</h3>
                    <p className="text-gray-600 font-medium">{stat.label}</p>
                  </CardContent>
                </Card>
              );
            })}
          </div>
        </div>
      </section>

      {/* Popular Cities - Airbnb Style Grid */}
      <section className="py-16 bg-gray-50">
        <div className="container mx-auto px-4 lg:px-8">
          <div className="text-center mb-12">
            <h2 className="text-4xl lg:text-5xl font-bold text-gray-900 mb-4">
              Explore Popular Cities
            </h2>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              Find PGs in India's most vibrant cities
            </p>
          </div>

          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
            {cities.map((city, index) => (
              <Link 
                key={city.name}
                to={`/search?city=${city.name.toLowerCase()}`}
                className="group"
              >
                <Card className="border-0 overflow-hidden shadow-lg hover:shadow-2xl transition-all duration-300 group-hover:scale-[1.02]">
                  <div className="relative h-64 overflow-hidden">
                    <img 
                      src={city.image} 
                      alt={city.name}
                      className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-500"
                    />
                    <div className={`absolute inset-0 bg-gradient-to-t ${city.color} opacity-70`} />
                    <div className="absolute bottom-0 left-0 right-0 p-6 text-white">
                      <h3 className="text-2xl font-bold mb-1">{city.name}</h3>
                      <p className="text-white/90">{city.properties} properties</p>
                    </div>
                    <div className="absolute top-4 right-4">
                      <Badge className="bg-white/20 backdrop-blur-md text-white border-white/30">
                        Popular
                      </Badge>
                    </div>
                  </div>
                </Card>
              </Link>
            ))}
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-16 bg-white">
        <div className="container mx-auto px-4 lg:px-8">
          <div className="text-center mb-16">
            <Badge className="mb-4 bg-blue-100 text-blue-700 border-blue-200 px-4 py-1">
              <Zap className="h-3 w-3 mr-1" />
              Why Choose Us
            </Badge>
            <h2 className="text-4xl lg:text-5xl font-bold text-gray-900 mb-6">
              Experience Hassle-Free PG Hunting
            </h2>
            <p className="text-xl text-gray-600 max-w-3xl mx-auto">
              We've simplified the entire process from search to move-in
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
            {features.map((feature, index) => {
              const Icon = feature.icon;
              return (
                <div key={index} className="text-center">
                  <div className={`inline-flex p-5 rounded-2xl ${feature.color} mb-6`}>
                    <Icon className="h-8 w-8" />
                  </div>
                  <h3 className="text-xl font-bold text-gray-900 mb-3">{feature.title}</h3>
                  <p className="text-gray-600">{feature.description}</p>
                </div>
              );
            })}
          </div>
        </div>
      </section>

      {/* Testimonials */}
      <section className="py-16 bg-gradient-to-b from-white to-blue-50">
        <div className="container mx-auto px-4 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl lg:text-5xl font-bold text-gray-900 mb-6">
              Loved by Residents
            </h2>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              See what our residents have to say about their experience
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {testimonials.map((testimonial, index) => (
              <Card key={index} className="border-0 shadow-xl hover:shadow-2xl transition-shadow duration-300">
                <CardContent className="p-8">
                  <div className="flex items-center gap-4 mb-6">
                    <Avatar className="h-14 w-14 border-4 border-white shadow-lg">
                      <AvatarImage src={testimonial.avatar} />
                      <AvatarFallback className="bg-gradient-to-br from-blue-100 to-purple-100 text-blue-700">
                        {testimonial.name.charAt(0)}
                      </AvatarFallback>
                    </Avatar>
                    <div>
                      <h4 className="font-bold text-lg">{testimonial.name}</h4>
                      <p className="text-gray-500 text-sm">{testimonial.role}</p>
                      <div className="flex items-center gap-1 mt-1">
                        <MapPin className="h-3 w-3 text-gray-400" />
                        <span className="text-xs text-gray-500">{testimonial.location}</span>
                      </div>
                    </div>
                  </div>
                  <p className="text-gray-700 mb-8 italic relative pl-6 before:content-'❝' before:absolute before:left-0 before:top-0 before:text-3xl before:text-blue-200">
                    {testimonial.content}
                  </p>
                  <div className="flex items-center justify-between">
                    <div className="flex">
                      {[...Array(5)].map((_, i) => (
                        <Star 
                          key={i} 
                          className={`h-5 w-5 ${i < testimonial.rating ? 'text-yellow-400 fill-yellow-400' : 'text-gray-300'}`}
                        />
                      ))}
                    </div>
                    <Badge variant="outline" className="text-xs">
                      Verified Resident
                    </Badge>
                  </div>
                </CardContent>
              </Card>
            ))}
          </div>
        </div>
      </section>

      {/* Final CTA */}
      <section className="relative py-24 overflow-hidden">
        {/* Background */}
        <div className="absolute inset-0 bg-gradient-to-br from-blue-600 via-purple-600 to-pink-600">
          <div className="absolute inset-0 bg-[url('https://images.unsplash.com/photo-1586023492125-27b2c045efd7?q=80&w=2058')] bg-cover bg-center opacity-10" />
        </div>
        
        {/* Floating Elements */}
        <div className="absolute top-1/4 left-10 w-96 h-96 bg-white/10 rounded-full blur-3xl" />
        <div className="absolute bottom-1/4 right-10 w-96 h-96 bg-white/10 rounded-full blur-3xl" />

        <div className="relative z-10 container mx-auto px-4 text-center">
          <Badge className="mb-6 bg-white/20 backdrop-blur-md text-white border-white/30 px-6 py-2">
            <Sparkles className="h-4 w-4 mr-2" />
            Limited Time Offer
          </Badge>
          
          <h2 className="text-5xl lg:text-7xl font-bold text-white mb-8">
            Ready to Find Your
            <span className="block bg-gradient-to-r from-yellow-300 via-pink-300 to-purple-300 bg-clip-text text-transparent">
              Perfect PG?
            </span>
          </h2>
          
          <p className="text-2xl text-blue-100 mb-12 max-w-3xl mx-auto">
            Join thousands of students and professionals who've found their ideal home with us
          </p>

          <div className="flex flex-col sm:flex-row gap-6 justify-center items-center">
            <Button 
              size="lg" 
              className="bg-white text-blue-700 hover:bg-gray-100 px-10 py-7 text-xl font-bold rounded-2xl shadow-2xl hover:shadow-3xl transition-all hover:scale-105"
              asChild
            >
              <Link to="/search">
                <Search className="mr-3 h-6 w-6" />
                Start Searching Now
                <ArrowRight className="ml-3 h-6 w-6" />
              </Link>
            </Button>
            
            <Button 
              size="lg" 
              variant="outline" 
              className="border-3 border-white text-white hover:bg-white/20 px-10 py-7 text-xl font-bold rounded-2xl backdrop-blur-sm hover:scale-105 transition-all"
              asChild
            >
              <Link to="/list-pg">
                <HomeIcon className="mr-3 h-6 w-6" />
                List Your PG
              </Link>
            </Button>
          </div>

          <div className="mt-12 grid grid-cols-3 gap-8 max-w-2xl mx-auto">
            <div className="text-center">
              <div className="text-3xl font-bold text-white mb-2">₹0</div>
              <div className="text-blue-200 text-sm">Booking Fees</div>
            </div>
            <div className="text-center">
              <div className="text-3xl font-bold text-white mb-2">24/7</div>
              <div className="text-blue-200 text-sm">Support Available</div>
            </div>
            <div className="text-center">
              <div className="text-3xl font-bold text-white mb-2">100%</div>
              <div className="text-blue-200 text-sm">Secure Payments</div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};