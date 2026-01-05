
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JobPortal kwork = new JobPortal("Kwork");

        kwork.addJob(new Job("j1", "Landing Page", 200, true));
        kwork.addJob(new Job("j2", "Web Application", 1500, true));
        kwork.addJob(new Job("j3", "Logo Design", 100, false));
        kwork.addJob(new Job("j4", "Mobile App", 3000, true));

        kwork.addFreelancer(new Freelancer("f1", "Yernur", "y@mail.com", new String[]{"React", "TypeScript"}, 800));
        kwork.addFreelancer(new Freelancer("f2", "Darkhan", "d@mail.com", new String[]{"Figma", "Photoshop"}, 1200));
        kwork.addFreelancer(new Freelancer("f3", "Alex", "a@mail.com", new String[]{"React", "Node.js"}, 500));
        kwork.addFreelancer(new Freelancer("f4", "John", "johndoe@gmail.com", new String[]{"Bitrix24", "Wordpress"}, 1000));

        System.out.println(kwork);

        System.out.println("Searching");
        System.out.println("Find job j2: " + kwork.findJob("j2"));
        System.out.println("Find freelancer f1: " + kwork.findFreelancer("f1"));


        System.out.println("\nFiltering:");
        for (Job j : kwork.findJobs()) {
            System.out.println(j);
        }

        System.out.println("\nFiltering by skill:");
        for (Freelancer f : kwork.findFreelancersBySkills("React")) {
            System.out.println(f);
        }

        System.out.println("\nFiltering by min budget 500");
        for (Job j : kwork.filterJobsByMinBudget(500)) {
            System.out.println(j);
        }

        System.out.println("\nSorting jobs by budget");
        for (Job j : kwork.getJobsSortedByBudget()) {
            System.out.println(j);
        }

        System.out.println("\nSorting freelancers by salary");
        for (Freelancer f : kwork.getFreelancersSortedBySalary()) {
            System.out.println(f);
        }
  }
}



