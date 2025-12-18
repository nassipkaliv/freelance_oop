
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Freelancer freelancer1 = new Freelancer("f1", "Yernur", "nassipkaliv@gmail.com", new String[]{"React", "TypeScript"}, 800);

        Client client1 = new Client("cleintId1", "Leha", "leha@bitrix.com", "Bitrix24");

        Job job1 = client1.postJob("jobId1", "Make Landing Page", 250);

        List<User> users = new ArrayList<>();
        users.add(freelancer1);
        users.add(client1);

        for(User user : users) {
            System.out.println("Role: " + user.getRole());
            user.displayInfo();
            System.out.println();
        }

        System.out.println("");

//        JobPortal kwork = new JobPortal("Kwork");
//
//        Job job1 = new Job("id1", "Landing page", 200, true);
//        Job job2 = new Job("id2", "Web Design", 600, false);
//        Job job3 = new Job("id1", "UI/UX Design", 999, true);
//
//        Freelancer freelancer1 = new Freelancer("id1", "Yernur", new String[]{"React", "TypeScript", "JavaScript", "PHP", "Tailwind", "Vite"}, 800);
//        Freelancer freelancer2 = new Freelancer("id2", "Darkhan", new String[]{"Figma", "Photoshop", "Adobe Illustrator"}, 1000);
//
//        kwork.addJob(job1);
//        kwork.addJob(job2);
//        kwork.addJob(job3);
//
//        kwork.addFreelancer(freelancer1);
//        kwork.addFreelancer(freelancer2);
//
//        System.out.println(kwork);
//
//        for(Job j: kwork.getJobs()) {
//            System.out.println(j);
//        }
//
//        for(Freelancer f: kwork.getFreelancers()) {
//            System.out.println(f);
//        }
//
//        List<Job> jobs = kwork.findJobs();
//        for(Job j : jobs) {
//            System.out.println(j);
//        }
//
//
//        System.out.print("TypeScript Devs:");
//        List<Freelancer> tsDevs = kwork.findFreelancersBySkills("TypeScript");
//        for(Freelancer f: tsDevs) {
//            System.out.println((f));
//        }

//        System.out.println(job1);
//        System.out.println(job2);
//        System.out.println(job3);
//
//        System.out.println(job1.equals(job2));
//        System.out.println(job1.equals(job3));
//
//        System.out.println(freelancer1);
//        System.out.println("Freelancer has TypeScript? " + freelancer1.hasAnySkills("react"));
    }
}




