import { Freelancer } from '../../types';
import { Button } from '../ui/Button';

interface FreelancerCardProps {
  freelancer: Freelancer;
  onEdit: (freelancer: Freelancer) => void;
  onDelete: (id: string) => void;
}

export function FreelancerCard({ freelancer, onEdit, onDelete }: FreelancerCardProps) {
  return (
    <div className="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-shadow">
      <div className="flex justify-between items-start mb-4">
        <div>
          <h3 className="text-lg font-semibold text-gray-900">{freelancer.name}</h3>
          <p className="text-sm text-gray-500">{freelancer.email}</p>
        </div>
        <div className="text-right">
          <span className="text-lg font-bold text-primary-600">
            ${freelancer.salary.toLocaleString()}
          </span>
          <p className="text-xs text-gray-500">/ year</p>
        </div>
      </div>

      <div className="mb-4">
        <p className="text-sm text-gray-600 mb-2">Skills:</p>
        <div className="flex flex-wrap gap-2">
          {freelancer.skills.map((skill, index) => (
            <span
              key={index}
              className="px-2 py-1 bg-primary-50 text-primary-700 text-xs font-medium rounded-full"
            >
              {skill}
            </span>
          ))}
          {freelancer.skills.length === 0 && (
            <span className="text-sm text-gray-400 italic">No skills listed</span>
          )}
        </div>
      </div>

      <div className="flex gap-2 pt-4 border-t border-gray-100">
        <Button
          variant="secondary"
          onClick={() => onEdit(freelancer)}
          className="flex-1 text-sm"
        >
          Edit
        </Button>
        <Button
          variant="danger"
          onClick={() => onDelete(freelancer.id)}
          className="flex-1 text-sm"
        >
          Delete
        </Button>
      </div>
    </div>
  );
}
