
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("Connection successfull!");
            DatabaseConnection.closeConnection();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // job crud

        JobDAO jobDAO = new JobDAO();

        try {
            // CREATE
            jobDAO.create(new Job("j1", "Landing page", 500, true));
            jobDAO.create(new Job("j2", "Mobile App", 3000, true));
            jobDAO.create(new Job("j3", "Logo Design", 200, false));

            // READ ALL
            List<Job> jobs = jobDAO.findAll();
            for (Job j : jobs) {
                System.out.println(j);
            }

            // READ BY ID
            Job found = jobDAO.findByid("j1");
            System.out.println("Found: " + found);

            // UPDATE
            found.setBudget(750);
            found.setTitle("Legacy code change");
            jobDAO.update(found);
            System.out.println("Updated: " + jobDAO.findByid("j1"));

            // DELETE
            jobDAO.delete("j3");
            System.out.println("After delete: ");
            for (Job j : jobDAO.findAll()) {
                System.out.println(j);
            }

            // freelancer crud
            FreelancerDAO freelancerDAO = new FreelancerDAO();
            freelancerDAO.create(new Freelancer("f1", "Yernur", "nassipkaliv@gmail.com", new String[]{"JS", "React", "TS"}, 1000));
            freelancerDAO.create(new Freelancer("f2", "Darkhan", "ditch@gmail.com", new String[]{"Figma", "n8n"}, 800));

            for (Freelancer f : freelancerDAO.findAll()) {
                System.out.println(f);
            }

            Freelancer f = freelancerDAO.findById("f1");
            f.setSalary(1500);
            freelancerDAO.update(f);
            System.out.println("Updated: " + freelancerDAO.findById("f1"));

            freelancerDAO.delete("f2");
            for(Freelancer fr: freelancerDAO.findAll()) {
                System.out.println(fr);
            }

        } catch (SQLException e) {
             e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }

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



