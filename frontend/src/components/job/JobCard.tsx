import { Job } from '../../types';
import { Button } from '../ui/Button';

interface JobCardProps {
  job: Job;
  onEdit: (job: Job) => void;
  onDelete: (id: string) => void;
}

export function JobCard({ job, onEdit, onDelete }: JobCardProps) {
  return (
    <div className="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-shadow">
      <div className="flex justify-between items-start mb-4">
        <div className="flex-1">
          <h3 className="text-lg font-semibold text-gray-900">{job.title}</h3>
          <span
            className={`inline-block mt-2 px-2 py-1 text-xs font-medium rounded-full ${
              job.isOpen
                ? 'bg-green-100 text-green-700'
                : 'bg-gray-100 text-gray-600'
            }`}
          >
            {job.isOpen ? 'Open' : 'Closed'}
          </span>
        </div>
        <div className="text-right">
          <span className="text-lg font-bold text-primary-600">
            ${job.budget.toLocaleString()}
          </span>
          <p className="text-xs text-gray-500">budget</p>
        </div>
      </div>

      <div className="flex gap-2 pt-4 border-t border-gray-100">
        <Button
          variant="secondary"
          onClick={() => onEdit(job)}
          className="flex-1 text-sm"
        >
          Edit
        </Button>
        <Button
          variant="danger"
          onClick={() => onDelete(job.id)}
          className="flex-1 text-sm"
        >
          Delete
        </Button>
      </div>
    </div>
  );
}
