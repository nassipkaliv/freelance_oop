package com.jobportal.repository;

import com.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {

    List<Job> findByIsOpenTrue();

    List<Job> findByIsOpenFalse();

    List<Job> findByBudgetGreaterThanEqual(double minBudget);

    List<Job> findByBudgetLessThanEqual(double maxBudget);

    List<Job> findByBudgetBetween(double minBudget, double maxBudget);

    List<Job> findByTitleContainingIgnoreCase(String keyword);

    @Query("SELECT j FROM Job j ORDER BY j.budget DESC")
    List<Job> findAllOrderByBudgetDesc();

    @Query("SELECT j FROM Job j ORDER BY j.budget ASC")
    List<Job> findAllOrderByBudgetAsc();
}