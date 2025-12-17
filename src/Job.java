import java.util.Objects;

public class Job {
    private String id;
    private String title;
    private double budget;
    private boolean isOpen;

    public Job(String id, String title, double budget, boolean isOpen) {
        this.id = id;
        this.title = title;
        setBudget(budget);
        this.isOpen = isOpen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        if(budget < 0) {
            System.out.println("Budget can not be less than 0");
            return;
        }
        this.budget = budget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void close() {
        this.isOpen = false;
    }

    public void open() {
        this.isOpen = true;
    }

    @Override
    public String toString() {
        return "Job: {" + "id='" + id + '\'' + ", title='" + title + '\'' + ", budget=" + budget + ", isOpen=" + isOpen + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}