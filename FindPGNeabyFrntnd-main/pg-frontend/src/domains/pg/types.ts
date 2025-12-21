// src/domains/pg/types.ts
export interface PgDetails {
    id: string;
    name: string;
    description: string;
    address: string;
    city: string;
    pricePerNight: number;
    thumbnail: string;
    images: string[];
    rating: number;
    reviewCount: number;
    amenities: string[];
    shared: boolean;
    maxOccupancy: number;
    ownerId: string;
    createdAt: string;
    updatedAt: string;
  }
  
  export interface PgFilter {
    city?: string;
    minPrice?: number;
    maxPrice?: number;
    amenities?: string[];
    shared?: boolean;
    checkIn?: string;
    checkOut?: string;
  }
  
  export interface PgSearchResult {
    items: PgDetails[];
    total: number;
    page: number;
    limit: number;
  }