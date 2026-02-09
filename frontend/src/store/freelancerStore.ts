import { create } from 'zustand';
import { Freelancer, FreelancerFormData } from '../types';
import { freelancerService } from '../services/freelancerService';

interface FreelancerState {
  freelancers: Freelancer[];
  loading: boolean;
  error: string | null;

  fetchFreelancers: () => Promise<void>;
  searchFreelancers: (name: string) => Promise<void>;
  createFreelancer: (data: FreelancerFormData) => Promise<void>;
  updateFreelancer: (id: string, data: FreelancerFormData) => Promise<void>;
  deleteFreelancer: (id: string) => Promise<void>;
  clearError: () => void;
}

export const useFreelancerStore = create<FreelancerState>((set) => ({
  freelancers: [],
  loading: false,
  error: null,

  fetchFreelancers: async () => {
    set({ loading: true, error: null });
    try {
      const freelancers = await freelancerService.getAll();
      set({ freelancers, loading: false });
    } catch (error) {
      set({ error: 'Failed to fetch freelancers', loading: false });
    }
  },

  searchFreelancers: async (name: string) => {
    set({ loading: true, error: null });
    try {
      const freelancers = name
        ? await freelancerService.search(name)
        : await freelancerService.getAll();
      set({ freelancers, loading: false });
    } catch (error) {
      set({ error: 'Failed to search freelancers', loading: false });
    }
  },

  createFreelancer: async (data: FreelancerFormData) => {
    set({ loading: true, error: null });
    try {
      const newFreelancer = await freelancerService.create(data);
      set((state) => ({
        freelancers: [...state.freelancers, newFreelancer],
        loading: false
      }));
    } catch (error) {
      set({ error: 'Failed to create freelancer', loading: false });
      throw error;
    }
  },

  updateFreelancer: async (id: string, data: FreelancerFormData) => {
    set({ loading: true, error: null });
    try {
      const updatedFreelancer = await freelancerService.update(id, data);
      set((state) => ({
        freelancers: state.freelancers.map((f) =>
          f.id === id ? updatedFreelancer : f
        ),
        loading: false,
      }));
    } catch (error) {
      set({ error: 'Failed to update freelancer', loading: false });
      throw error;
    }
  },

  deleteFreelancer: async (id: string) => {
    set({ loading: true, error: null });
    try {
      await freelancerService.delete(id);
      set((state) => ({
        freelancers: state.freelancers.filter((f) => f.id !== id),
        loading: false,
      }));
    } catch (error) {
      set({ error: 'Failed to delete freelancer', loading: false });
      throw error;
    }
  },

  clearError: () => set({ error: null }),
}));
