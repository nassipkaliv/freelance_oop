package com.jobportal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @Min(value = 0, message = "Budget cannot be negative")
    @Column(nullable = false)
    private double budget;

    @Column(name = "is_open", nullable = false)
    private boolean isOpen;

    public Job() {
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        if (budget < 0) {
            throw new IllegalArgumentException("Budget cannot be negative");
        }
        this.budget = budget;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void close() {
        this.isOpen = false;
    }

    public void open() {
        this.isOpen = true;
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

    @Override
    public String toString() {
        return "Job{id='" + id + "', title='" + title + "', budget=" + budget + ", isOpen=" + isOpen + "}";
    }
}