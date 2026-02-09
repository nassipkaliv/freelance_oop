import api from './api';
import { Freelancer, FreelancerFormData } from '../types';

export const freelancerService = {
  getAll: async (): Promise<Freelancer[]> => {
    const response = await api.get('/freelancers');
    return response.data;
  },

  getById: async (id: string): Promise<Freelancer> => {
    const response = await api.get(`/freelancers/${id}`);
    return response.data;
  },

  search: async (name: string): Promise<Freelancer[]> => {
    const response = await api.get(`/freelancers/search?name=${encodeURIComponent(name)}`);
    return response.data;
  },

  getBySkill: async (skill: string): Promise<Freelancer[]> => {
    const response = await api.get(`/freelancers/skill/${encodeURIComponent(skill)}`);
    return response.data;
  },

  getSortedBySalary: async (): Promise<Freelancer[]> => {
    const response = await api.get('/freelancers/sorted');
    return response.data;
  },

  create: async (data: FreelancerFormData): Promise<Freelancer> => {
    const response = await api.post('/freelancers', data);
    return response.data;
  },

  update: async (id: string, data: FreelancerFormData): Promise<Freelancer> => {
    const response = await api.put(`/freelancers/${id}`, data);
    return response.data;
  },

  delete: async (id: string): Promise<void> => {
    await api.delete(`/freelancers/${id}`);
  },
};
