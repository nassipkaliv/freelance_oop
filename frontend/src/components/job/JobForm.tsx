import { useState, useEffect } from 'react';
import { Job, JobFormData } from '../../types';
import { Button } from '../ui/Button';
import { Input } from '../ui/Input';

interface JobFormProps {
  job?: Job | null;
  onSubmit: (data: JobFormData) => void;
  onCancel: () => void;
  isLoading?: boolean;
}

export function JobForm({
  job,
  onSubmit,
  onCancel,
  isLoading
}: JobFormProps) {
  const [formData, setFormData] = useState<JobFormData>({
    title: '',
    budget: 0,
    isOpen: true,
  });

  useEffect(() => {
    if (job) {
      setFormData({
        title: job.title,
        budget: job.budget,
        isOpen: job.isOpen,
      });
    }
  }, [job]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(formData);
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <Input
        label="Job Title"
        value={formData.title}
        onChange={(e) => setFormData({ ...formData, title: e.target.value })}
        placeholder="Senior React Developer"
        required
      />

      <Input
        label="Budget"
        type="number"
        value={formData.budget}
        onChange={(e) => setFormData({ ...formData, budget: Number(e.target.value) })}
        min={0}
        required
      />

      <div>
        <label className="flex items-center space-x-3 cursor-pointer">
          <input
            type="checkbox"
            checked={formData.isOpen}
            onChange={(e) => setFormData({ ...formData, isOpen: e.target.checked })}
            className="w-5 h-5 text-primary-600 border-gray-300 rounded focus:ring-primary-500"
          />
          <span className="text-sm font-medium text-gray-700">
            Job is open for applications
          </span>
        </label>
      </div>

      <div className="flex gap-2 pt-4">
        <Button type="submit" disabled={isLoading} className="flex-1">
          {isLoading ? 'Saving...' : job ? 'Update' : 'Create'}
        </Button>
        <Button type="button" variant="secondary" onClick={onCancel} className="flex-1">
          Cancel
        </Button>
      </div>
    </form>
  );
}
