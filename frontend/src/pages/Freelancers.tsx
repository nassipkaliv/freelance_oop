import { useEffect, useState, useCallback } from 'react';
import { useFreelancerStore } from '../store/freelancerStore';
import { FreelancerList } from '../components/freelancer/FreelancerList';
import { FreelancerForm } from '../components/freelancer/FreelancerForm';
import { SearchBar } from '../components/ui/SearchBar';
import { Button } from '../components/ui/Button';
import { Modal } from '../components/ui/Modal';
import { Freelancer, FreelancerFormData } from '../types';

export function Freelancers() {
  const {
    freelancers,
    loading,
    error,
    fetchFreelancers,
    searchFreelancers,
    createFreelancer,
    updateFreelancer,
    deleteFreelancer,
    clearError,
  } = useFreelancerStore();

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingFreelancer, setEditingFreelancer] = useState<Freelancer | null>(null);

  useEffect(() => {
    fetchFreelancers();
  }, [fetchFreelancers]);

  const handleSearch = useCallback((query: string) => {
    searchFreelancers(query);
  }, [searchFreelancers]);

  const handleCreate = () => {
    setEditingFreelancer(null);
    setIsModalOpen(true);
  };

  const handleEdit = (freelancer: Freelancer) => {
    setEditingFreelancer(freelancer);
    setIsModalOpen(true);
  };

  const handleDelete = async (id: string) => {
    if (window.confirm('Are you sure you want to delete this freelancer?')) {
      await deleteFreelancer(id);
    }
  };

  const handleSubmit = async (data: FreelancerFormData) => {
    try {
      if (editingFreelancer) {
        await updateFreelancer(editingFreelancer.id, data);
      } else {
        await createFreelancer(data);
      }
      setIsModalOpen(false);
      setEditingFreelancer(null);
    } catch {
      // Error is handled in store
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingFreelancer(null);
    clearError();
  };

  return (
    <div>
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4 mb-6">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Freelancers</h1>
          <p className="text-gray-600">Manage your freelancer database</p>
        </div>
        <Button onClick={handleCreate}>
          Add Freelancer
        </Button>
      </div>

      {error && (
        <div className="mb-4 p-4 bg-red-50 border border-red-200 rounded-lg text-red-700">
          {error}
        </div>
      )}

      <div className="mb-6">
        <SearchBar
          onSearch={handleSearch}
          placeholder="Search freelancers by name..."
        />
      </div>

      <FreelancerList
        freelancers={freelancers}
        loading={loading}
        onEdit={handleEdit}
        onDelete={handleDelete}
      />

      <Modal
        isOpen={isModalOpen}
        onClose={handleCloseModal}
        title={editingFreelancer ? 'Edit Freelancer' : 'Add Freelancer'}
      >
        <FreelancerForm
          freelancer={editingFreelancer}
          onSubmit={handleSubmit}
          onCancel={handleCloseModal}
          isLoading={loading}
        />
      </Modal>
    </div>
  );
}
