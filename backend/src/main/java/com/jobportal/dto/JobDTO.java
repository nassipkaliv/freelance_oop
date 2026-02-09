package com.jobportal.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class JobDTO {

    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    @Min(value = 0, message = "Budget cannot be negative")
    private double budget;

    private boolean isOpen;

    public JobDTO() {
    }

    public JobDTO(String id, String title, double budget, boolean isOpen) {
        this.id = id;
        this.title = title;
        this.budget = budget;
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
        this.budget = budget;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}