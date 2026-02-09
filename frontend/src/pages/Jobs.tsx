import { useEffect, useState, useCallback } from 'react';
import { useJobStore } from '../store/jobStore';
import { JobList } from '../components/job/JobList';
import { JobForm } from '../components/job/JobForm';
import { SearchBar } from '../components/ui/SearchBar';
import { Button } from '../components/ui/Button';
import { Modal } from '../components/ui/Modal';
import { Job, JobFormData } from '../types';

export function Jobs() {
  const {
    jobs,
    loading,
    error,
    showOnlyOpen,
    fetchJobs,
    searchJobs,
    createJob,
    updateJob,
    deleteJob,
    setShowOnlyOpen,
    clearError,
  } = useJobStore();

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingJob, setEditingJob] = useState<Job | null>(null);

  useEffect(() => {
    fetchJobs();
  }, [fetchJobs, showOnlyOpen]);

  const handleSearch = useCallback((query: string) => {
    searchJobs(query);
  }, [searchJobs]);

  const handleCreate = () => {
    setEditingJob(null);
    setIsModalOpen(true);
  };

  const handleEdit = (job: Job) => {
    setEditingJob(job);
    setIsModalOpen(true);
  };

  const handleDelete = async (id: string) => {
    if (window.confirm('Are you sure you want to delete this job?')) {
      await deleteJob(id);
    }
  };

  const handleSubmit = async (data: JobFormData) => {
    try {
      if (editingJob) {
        await updateJob(editingJob.id, data);
      } else {
        await createJob(data);
      }
      setIsModalOpen(false);
      setEditingJob(null);
    } catch {
      // Error is handled in store
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingJob(null);
    clearError();
  };

  const displayedJobs = showOnlyOpen ? jobs.filter((job) => job.isOpen) : jobs;

  return (
    <div>
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4 mb-6">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Jobs</h1>
          <p className="text-gray-600">Manage job postings</p>
        </div>
        <Button onClick={handleCreate}>
          Post Job
        </Button>
      </div>

      {error && (
        <div className="mb-4 p-4 bg-red-50 border border-red-200 rounded-lg text-red-700">
          {error}
        </div>
      )}

      <div className="flex flex-col sm:flex-row gap-4 mb-6">
        <div className="flex-1">
          <SearchBar
            onSearch={handleSearch}
            placeholder="Search jobs by title..."
          />
        </div>
        <label className="flex items-center space-x-2 cursor-pointer">
          <input
            type="checkbox"
            checked={showOnlyOpen}
            onChange={(e) => setShowOnlyOpen(e.target.checked)}
            className="w-4 h-4 text-primary-600 border-gray-300 rounded focus:ring-primary-500"
          />
          <span className="text-sm text-gray-700">Show only open jobs</span>
        </label>
      </div>

      <JobList
        jobs={displayedJobs}
        loading={loading}
        onEdit={handleEdit}
        onDelete={handleDelete}
      />

      <Modal
        isOpen={isModalOpen}
        onClose={handleCloseModal}
        title={editingJob ? 'Edit Job' : 'Post New Job'}
      >
        <JobForm
          job={editingJob}
          onSubmit={handleSubmit}
          onCancel={handleCloseModal}
          isLoading={loading}
        />
      </Modal>
    </div>
  );
}
