package com.jobportal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "freelancers")
public class Freelancer extends User {

    @ElementCollection
    @CollectionTable(name = "freelancer_skills", joinColumns = @JoinColumn(name = "freelancer_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @Min(value = 0, message = "Salary cannot be negative")
    @Column(nullable = false)
    private double salary;

    public Freelancer() {
        super();
    }

    public Freelancer(String id, String name, String email, String[] skills, double salary) {
        super(id, name, email);
        this.skills = skills != null ? new ArrayList<>(Arrays.asList(skills)) : new ArrayList<>();
        this.salary = salary;
    }

    public Freelancer(String id, String name, String email, List<String> skills, double salary) {
        super(id, name, email);
        this.skills = skills != null ? new ArrayList<>(skills) : new ArrayList<>();
        this.salary = salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills != null ? new ArrayList<>(skills) : new ArrayList<>();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }

    @Override
    public String getRole() {
        return "Freelancer";
    }

    public boolean hasSkill(String skill) {
        if (skill == null || skills == null) return false;
        return skills.stream()
                .anyMatch(s -> s != null && s.equalsIgnoreCase(skill));
    }

    @Override
    public String toString() {
        return "Freelancer{id='" + getId() + "', name='" + getName() +
               "', skills=" + skills + ", salary=" + salary + "}";
    }
}