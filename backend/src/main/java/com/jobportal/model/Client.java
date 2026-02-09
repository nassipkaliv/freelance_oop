package com.jobportal.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends User {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private List<Job> jobs = new ArrayList<>();

    public Client() {
        super();
    }

    public Client(String id, String name, String email) {
        super(id, name, email);
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs != null ? new ArrayList<>(jobs) : new ArrayList<>();
    }

    public void addJob(Job job) {
        if (job != null) {
            jobs.add(job);
        }
    }

    @Override
    public String getRole() {
        return "Client";
    }

    @Override
    public String toString() {
        return "Client{id='" + getId() + "', name='" + getName() +
               "', email='" + getEmail() + "', jobsCount=" + jobs.size() + "}";
    }
}