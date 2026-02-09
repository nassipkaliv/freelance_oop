import { useState, useEffect } from 'react';
import { Freelancer, FreelancerFormData } from '../../types';
import { Button } from '../ui/Button';
import { Input } from '../ui/Input';

interface FreelancerFormProps {
  freelancer?: Freelancer | null;
  onSubmit: (data: FreelancerFormData) => void;
  onCancel: () => void;
  isLoading?: boolean;
}

export function FreelancerForm({
  freelancer,
  onSubmit,
  onCancel,
  isLoading
}: FreelancerFormProps) {
  const [formData, setFormData] = useState<FreelancerFormData>({
    name: '',
    email: '',
    skills: [],
    salary: 0,
  });
  const [skillInput, setSkillInput] = useState('');

  useEffect(() => {
    if (freelancer) {
      setFormData({
        name: freelancer.name,
        email: freelancer.email,
        skills: freelancer.skills,
        salary: freelancer.salary,
      });
    }
  }, [freelancer]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(formData);
  };

  const addSkill = () => {
    if (skillInput.trim() && !formData.skills.includes(skillInput.trim())) {
      setFormData({
        ...formData,
        skills: [...formData.skills, skillInput.trim()],
      });
      setSkillInput('');
    }
  };

  const removeSkill = (skillToRemove: string) => {
    setFormData({
      ...formData,
      skills: formData.skills.filter((skill) => skill !== skillToRemove),
    });
  };

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') {
      e.preventDefault();
      addSkill();
    }
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <Input
        label="Name"
        value={formData.name}
        onChange={(e) => setFormData({ ...formData, name: e.target.value })}
        placeholder="John Doe"
        required
      />

      <Input
        label="Email"
        type="email"
        value={formData.email}
        onChange={(e) => setFormData({ ...formData, email: e.target.value })}
        placeholder="john@example.com"
        required
      />

      <Input
        label="Salary (per year)"
        type="number"
        value={formData.salary}
        onChange={(e) => setFormData({ ...formData, salary: Number(e.target.value) })}
        min={0}
        required
      />

      <div>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Skills
        </label>
        <div className="flex gap-2">
          <Input
            value={skillInput}
            onChange={(e) => setSkillInput(e.target.value)}
            onKeyDown={handleKeyDown}
            placeholder="Add a skill..."
          />
          <Button type="button" variant="secondary" onClick={addSkill}>
            Add
          </Button>
        </div>
        <div className="flex flex-wrap gap-2 mt-2">
          {formData.skills.map((skill, index) => (
            <span
              key={index}
              className="inline-flex items-center px-2 py-1 bg-primary-50 text-primary-700 text-sm rounded-full"
            >
              {skill}
              <button
                type="button"
                onClick={() => removeSkill(skill)}
                className="ml-1 text-primary-500 hover:text-primary-700"
              >
                <svg className="w-4 h-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
            </span>
          ))}
        </div>
      </div>

      <div className="flex gap-2 pt-4">
        <Button type="submit" disabled={isLoading} className="flex-1">
          {isLoading ? 'Saving...' : freelancer ? 'Update' : 'Create'}
        </Button>
        <Button type="button" variant="secondary" onClick={onCancel} className="flex-1">
          Cancel
        </Button>
      </div>
    </form>
  );
}
