import java.util.Arrays;
import java.util.Objects;

public class  Freelancer {
    private String id;
    private String name;
    private String[] skills;
    private double salary;

    public Freelancer(String id, String name, String[] skills, double salary) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Freelancer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", skills=" + Arrays.toString(skills) +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Freelancer that = (Freelancer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
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

