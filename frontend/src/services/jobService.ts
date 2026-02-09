import api from './api';
import { Job, JobFormData } from '../types';

export const jobService = {
  getAll: async (): Promise<Job[]> => {
    const response = await api.get('/jobs');
    return response.data;
  },

  getById: async (id: string): Promise<Job> => {
    const response = await api.get(`/jobs/${id}`);
    return response.data;
  },

  getOpen: async (): Promise<Job[]> => {
    const response = await api.get('/jobs/open');
    return response.data;
  },

  search: async (keyword: string): Promise<Job[]> => {
    const response = await api.get(`/jobs/search?keyword=${encodeURIComponent(keyword)}`);
    return response.data;
  },

  getByMinBudget: async (minBudget: number): Promise<Job[]> => {
    const response = await api.get(`/jobs/filter?minBudget=${minBudget}`);
    return response.data;
  },

  getSortedByBudget: async (): Promise<Job[]> => {
    const response = await api.get('/jobs/sorted');
    return response.data;
  },

  create: async (data: JobFormData): Promise<Job> => {
    const response = await api.post('/jobs', data);
    return response.data;
  },

  update: async (id: string, data: JobFormData): Promise<Job> => {
    const response = await api.put(`/jobs/${id}`, data);
    return response.data;
  },

  delete: async (id: string): Promise<void> => {
    await api.delete(`/jobs/${id}`);
  },
};
