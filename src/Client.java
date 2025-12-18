import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private String company;
    private List<Job> postedJobs;

    public Client(String id, String name, String email, String company) {
        super(id,name,email);
        this.company = company;
        this.postedJobs = new ArrayList<>();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Job> getPostedJobs() {
        return postedJobs;
    }

    public void setPostedJobs(List<Job> postedJobs) {
        this.postedJobs = postedJobs;
    }

    public Job postJob(String jobId, String title, double budget) {
        Job job = new Job(jobId, title, budget, true);
        postedJobs.add(job);
        return job;
    }

    @Override
    public void displayInfo() {
        System.out.println("Client: " + getName());
        System.out.println("Company: " + getCompany());
        System.out.println("Email: " + getEmail());
        System.out.println("Posted jobs: " + postedJobs.size());
    }

    @Override
    public String getRole() {
        return "Client";
    }

    @Override
    public String toString() {
        return "Client{id='" + getId() + "', name='" + getName() +
                "', company='" + company + "', jobs=" + postedJobs.size() + "}";
    }
}