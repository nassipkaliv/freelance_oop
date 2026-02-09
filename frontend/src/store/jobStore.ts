import { create } from 'zustand';
import { Job, JobFormData } from '../types';
import { jobService } from '../services/jobService';

interface JobState {
  jobs: Job[];
  loading: boolean;
  error: string | null;
  showOnlyOpen: boolean;

  fetchJobs: () => Promise<void>;
  fetchOpenJobs: () => Promise<void>;
  searchJobs: (keyword: string) => Promise<void>;
  createJob: (data: JobFormData) => Promise<void>;
  updateJob: (id: string, data: JobFormData) => Promise<void>;
  deleteJob: (id: string) => Promise<void>;
  setShowOnlyOpen: (value: boolean) => void;
  clearError: () => void;
}

export const useJobStore = create<JobState>((set, get) => ({
  jobs: [],
  loading: false,
  error: null,
  showOnlyOpen: false,

  fetchJobs: async () => {
    set({ loading: true, error: null });
    try {
      const jobs = get().showOnlyOpen
        ? await jobService.getOpen()
        : await jobService.getAll();
      set({ jobs, loading: false });
    } catch (error) {
      set({ error: 'Failed to fetch jobs', loading: false });
    }
  },

  fetchOpenJobs: async () => {
    set({ loading: true, error: null });
    try {
      const jobs = await jobService.getOpen();
      set({ jobs, loading: false });
    } catch (error) {
      set({ error: 'Failed to fetch open jobs', loading: false });
    }
  },

  searchJobs: async (keyword: string) => {
    set({ loading: true, error: null });
    try {
      const jobs = keyword
        ? await jobService.search(keyword)
        : await jobService.getAll();
      set({ jobs, loading: false });
    } catch (error) {
      set({ error: 'Failed to search jobs', loading: false });
    }
  },

  createJob: async (data: JobFormData) => {
    set({ loading: true, error: null });
    try {
      const newJob = await jobService.create(data);
      set((state) => ({
        jobs: [...state.jobs, newJob],
        loading: false
      }));
    } catch (error) {
      set({ error: 'Failed to create job', loading: false });
      throw error;
    }
  },

  updateJob: async (id: string, data: JobFormData) => {
    set({ loading: true, error: null });
    try {
      const updatedJob = await jobService.update(id, data);
      set((state) => ({
        jobs: state.jobs.map((j) => j.id === id ? updatedJob : j),
        loading: false,
      }));
    } catch (error) {
      set({ error: 'Failed to update job', loading: false });
      throw error;
    }
  },

  deleteJob: async (id: string) => {
    set({ loading: true, error: null });
    try {
      await jobService.delete(id);
      set((state) => ({
        jobs: state.jobs.filter((j) => j.id !== id),
        loading: false,
      }));
    } catch (error) {
      set({ error: 'Failed to delete job', loading: false });
      throw error;
    }
  },

  setShowOnlyOpen: (value: boolean) => {
    set({ showOnlyOpen: value });
  },

  clearError: () => set({ error: null }),
}));
