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




}
