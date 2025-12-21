// src/services/http.ts
import axios, {
    type AxiosError,
    type AxiosInstance,
    type AxiosResponse,
    type InternalAxiosRequestConfig
} from 'axios';
  
  // Create a base axios instance with default config
  const http: AxiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_URL || 'http://localhost:5000/api',
    timeout: 10000,
    headers: {
      'Content-Type': 'application/json',
    },
  });
  
  // Request interceptor for adding auth token
  http.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
      const token = localStorage.getItem('token');
      if (token && config.headers) {
        config.headers.Authorization = `Bearer ${token}`;
      }
      return config;
    },
    (error: AxiosError) => {
      return Promise.reject(error);
    }
  );
  
  // Response interceptor for error handling
  http.interceptors.response.use(
    (response: AxiosResponse) => {
      return response;
    },
    (error: AxiosError) => {
      // Handle common errors
      if (error.response) {
        switch (error.response.status) {
          case 401:
            // Clear token and redirect to login
            localStorage.removeItem('token');
            window.location.href = '/login';
            break;
          case 403:
            console.error('Forbidden: You do not have permission');
            break;
          case 404:
            console.error('Resource not found');
            break;
          case 500:
            console.error('Server error');
            break;
          default:
            console.error('An error occurred');
        }
      }
      return Promise.reject(error);
    }
  );
  
  export { http };
