import java.util.Arrays;
import java.util.Objects;

public class Freelancer extends User {
    private String[] skills;
    private double salary;

    public Freelancer(String id, String name, String email, String[] skills, double salary) {
        super(id, name, email);
        this.skills = skills;
        this.salary = salary;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void displayInfo() {
        System.out.println("Freelancer: " + getName());
        System.out.println("Email" + getEmail());
        System.out.println("Skills" + Arrays.toString(skills));
        System.out.println("Salary:" + salary);
    }

    @Override
    public String getRole() {
        return "Freelancer";
    }

    @Override
    public String toString() {
        return "Freelancer{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", skills=" + Arrays.toString(skills) +
                ", salary=" + salary +
                '}';
    }

    public boolean hasAnySkills(String skill) {
        if (skill == null || skills == null) return false;

        for (String s : skills) {
            if (s != null && s.equalsIgnoreCase(skill)) {
                return true;
            }
        }
        return false;
    }
}

