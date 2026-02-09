package com.jobportal.repository;

import com.jobportal.model.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, String> {

    @Query("SELECT f FROM Freelancer f JOIN f.skills s WHERE LOWER(s) = LOWER(:skill)")
    List<Freelancer> findBySkill(@Param("skill") String skill);

    List<Freelancer> findBySalaryGreaterThanEqual(double minSalary);

    List<Freelancer> findBySalaryLessThanEqual(double maxSalary);

    List<Freelancer> findByNameContainingIgnoreCase(String name);

    @Query("SELECT f FROM Freelancer f ORDER BY f.salary DESC")
    List<Freelancer> findAllOrderBySalaryDesc();

    @Query("SELECT f FROM Freelancer f ORDER BY f.salary ASC")
    List<Freelancer> findAllOrderBySalaryAsc();
}