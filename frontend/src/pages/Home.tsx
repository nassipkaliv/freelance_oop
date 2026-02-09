import { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useFreelancerStore } from '../store/freelancerStore';
import { useJobStore } from '../store/jobStore';

export function Home() {
  const { freelancers, fetchFreelancers } = useFreelancerStore();
  const { jobs, fetchJobs } = useJobStore();

  useEffect(() => {
    fetchFreelancers();
    fetchJobs();
  }, [fetchFreelancers, fetchJobs]);

  const openJobs = jobs.filter((job) => job.isOpen);
  const totalBudget = jobs.reduce((sum, job) => sum + job.budget, 0);
  const avgSalary = freelancers.length
    ? freelancers.reduce((sum, f) => sum + f.salary, 0) / freelancers.length
    : 0;

  const stats = [
    {
      label: 'Freelancers',
      value: freelancers.length,
      link: '/freelancers',
      color: 'bg-blue-500',
    },
    {
      label: 'Total Jobs',
      value: jobs.length,
      link: '/jobs',
      color: 'bg-green-500',
    },
    {
      label: 'Open Jobs',
      value: openJobs.length,
      link: '/jobs',
      color: 'bg-yellow-500',
    },
    {
      label: 'Total Budget',
      value: `$${totalBudget.toLocaleString()}`,
      link: '/jobs',
      color: 'bg-purple-500',
    },
  ];

  return (
    <div>
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900">Welcome to Job Portal</h1>
        <p className="mt-2 text-gray-600">
          Manage freelancers and job postings in one place
        </p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        {stats.map((stat) => (
          <Link
            key={stat.label}
            to={stat.link}
            className="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-shadow"
          >
            <div className="flex items-center">
              <div className={`w-12 h-12 ${stat.color} rounded-lg flex items-center justify-center`}>
                <span className="text-white text-xl font-bold">
                  {typeof stat.value === 'number' ? stat.value : '#'}
                </span>
              </div>
              <div className="ml-4">
                <p className="text-sm text-gray-500">{stat.label}</p>
                <p className="text-xl font-semibold text-gray-900">
                  {typeof stat.value === 'string' ? stat.value : stat.value}
                </p>
              </div>
            </div>
          </Link>
        ))}
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
        <div className="bg-white rounded-xl shadow-md p-6">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-lg font-semibold text-gray-900">Recent Freelancers</h2>
            <Link to="/freelancers" className="text-primary-600 hover:text-primary-700 text-sm font-medium">
              View all
            </Link>
          </div>
          <div className="space-y-3">
            {freelancers.slice(0, 5).map((freelancer) => (
              <div key={freelancer.id} className="flex justify-between items-center py-2 border-b border-gray-100 last:border-0">
                <div>
                  <p className="font-medium text-gray-900">{freelancer.name}</p>
                  <p className="text-sm text-gray-500">{freelancer.email}</p>
                </div>
                <p className="font-semibold text-primary-600">
                  ${freelancer.salary.toLocaleString()}
                </p>
              </div>
            ))}
            {freelancers.length === 0 && (
              <p className="text-gray-500 text-center py-4">No freelancers yet</p>
            )}
          </div>
        </div>

        <div className="bg-white rounded-xl shadow-md p-6">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-lg font-semibold text-gray-900">Recent Jobs</h2>
            <Link to="/jobs" className="text-primary-600 hover:text-primary-700 text-sm font-medium">
              View all
            </Link>
          </div>
          <div className="space-y-3">
            {jobs.slice(0, 5).map((job) => (
              <div key={job.id} className="flex justify-between items-center py-2 border-b border-gray-100 last:border-0">
                <div className="flex items-center gap-2">
                  <span className={`w-2 h-2 rounded-full ${job.isOpen ? 'bg-green-500' : 'bg-gray-400'}`}></span>
                  <p className="font-medium text-gray-900">{job.title}</p>
                </div>
                <p className="font-semibold text-primary-600">
                  ${job.budget.toLocaleString()}
                </p>
              </div>
            ))}
            {jobs.length === 0 && (
              <p className="text-gray-500 text-center py-4">No jobs yet</p>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
