// src/domains/pg/api.ts

import { http } from '@/services/http';
import type { PgDetails, PgFilter, PgSearchResult } from './types';

export const pgApi = {
  // Get PG by ID
  getPgById: async (id: string): Promise<PgDetails> => {
    const response = await http.get(`/pgs/${id}`);
    return response.data;
  },

  // Search PGs with filters
  searchPgs: async (filters: PgFilter): Promise<PgSearchResult> => {
    const response = await http.get('/pgs/search', { params: filters });
    return response.data;
  },

  // Get featured PGs
  getFeaturedPgs: async (limit: number = 10): Promise<PgDetails[]> => {
    const response = await http.get('/pgs/featured', { params: { limit } });
    return response.data;
  },

  // Get PGs by city
  getPgsByCity: async (city: string): Promise<PgDetails[]> => {
    const response = await http.get('/pgs/city', { params: { city } });
    return response.data;
  },

  // Check availability for specific dates
  checkAvailability: async (
    pgId: string, 
    checkIn: string, 
    checkOut: string
  ): Promise<{ available: boolean; message?: string }> => {
    const response = await http.get(`/pgs/${pgId}/availability`, {
      params: { checkIn, checkOut }
    });
    return response.data;
  },

  // Get similar PGs (recommendations)
  getSimilarPgs: async (pgId: string): Promise<PgDetails[]> => {
    const response = await http.get(`/pgs/${pgId}/similar`);
    return response.data;
  }
};