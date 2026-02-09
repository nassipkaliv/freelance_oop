export interface Freelancer {
  id: string;
  name: string;
  email: string;
  skills: string[];
  salary: number;
}

export interface FreelancerFormData {
  name: string;
  email: string;
  skills: string[];
  salary: number;
}

export interface Job {
  id: string;
  title: string;
  budget: number;
  isOpen: boolean;
}

export interface JobFormData {
  title: string;
  budget: number;
  isOpen: boolean;
}
