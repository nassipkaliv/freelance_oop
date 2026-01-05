import java.util.ArrayList;
import java.util.List;

public class JobPortal {
    private String name;
    private List<Job> jobs;
    private List<Freelancer> freelancers;

    public JobPortal(String name) {
        this.name = name;
        this.jobs = new ArrayList<>();
        this.freelancers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Freelancer> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(List<Freelancer> freelancers) {
        this.freelancers = freelancers;
    }


    @Override
    public String toString() {
        return "JobPortal{" +
                "name='" + name + '\'' +
                ", jobs=" + jobs +
                ", freelancers=" + freelancers +
                '}';
    }

    public void addJob(Job job) {
        if (job == null) return;
        jobs.add(job);
    }

    public void addFreelancer(Freelancer freelancer) {
        if (freelancer == null) return;
        freelancers.add(freelancer);
    }

    public Job findJob(String id) {
        if (id == null) return null;
        for (Job job : jobs) {
            if (id.equals(job.getId())) return job;
        }
        return null;
    }

    public List<Job> findJobs() {
        List <Job> result = new ArrayList<>();

        for (Job job : jobs) {
            if (job.isOpen()) {
                result.add(job);
            }
        }
        return result;
    }

    public Freelancer findFreelancer(String id) {
        if (id == null) return null;
        for (Freelancer f : freelancers) {
            if(id.equals(f.getId())) return f;
        }
        return null;
    }

    public List<Freelancer> findFreelancersBySkills(String skill) {
        List<Freelancer> result = new ArrayList<>();
        for (Freelancer f : freelancers) {
            if(f.hasAnySkills(skill)) {
                result.add(f);
            }
        }
        return result;
    }

    public List<Job> filterJobsByMinBudget(double minBudget) {
        List<Job> result = new ArrayList<>();
        for (Job job : jobs) {
            if (job.getBudget() >= minBudget) {
                result.add(job);
            }
        }
        return result;
    }

    public List<Job> getJobsSortedByBudget() {
        List<Job> sortedList = new ArrayList<>(jobs);
        int n = sortedList.size();
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                if(sortedList.get(j).getBudget() < sortedList.get(j+1).getBudget()) {
                    Job temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j+1));
                    sortedList.set(j+1, temp);
                }
            }
        }
        return sortedList;
    }

    public List<Freelancer> getFreelancersSortedBySalary() {
        List<Freelancer> sortedList = new ArrayList<>(freelancers);
        int n = sortedList.size();
        for(int i = 1; i < n; i++) {
            Freelancer key = sortedList.get(i);
            int j = i - 1;
            while (j >= 0 && sortedList.get(j).getSalary() < key.getSalary()) {
                sortedList.set(j+1, sortedList.get(j));
                j--;
            }
            sortedList.set(j+1, key);
        }
        return  sortedList;
    }
}
