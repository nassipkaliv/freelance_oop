public class Job {
    private String id;
    private String title;
    private double budget;
    private boolean isOpen;

    public Job(String id, String title, double budget, boolean isOpen) {
        this.id = id;
        this.title = title;
        this.budget = budget;
        this.isOpen = isOpen;
    }

    @Override
    public String toString() {
        return "Job: " +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", budget=" + budget +
                ", isOpen=" + isOpen;
    }
}