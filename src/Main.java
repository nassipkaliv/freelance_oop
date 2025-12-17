
public class Main {
    public static void main(String[] args) {
        Job job1 = new Job("id1", "Landing page", 200, true);
        Job job2 = new Job("id2", "Web Design", 600, false);
        Job job3 = new Job("id1", "UI/UX Design", 999, true);

        System.out.println(job1);
        System.out.println(job2);
        System.out.println(job3);

        System.out.println(job1.equals(job2));
        System.out.println(job1.equals(job3));

    }
}




