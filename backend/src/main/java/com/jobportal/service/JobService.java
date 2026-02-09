package com.jobportal.service;

import com.jobportal.dto.JobDTO;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.model.Job;
import com.jobportal.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public JobDTO getJobById(String id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", id));
        return convertToDTO(job);
    }

    public List<JobDTO> getOpenJobs() {
        return jobRepository.findByIsOpenTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<JobDTO> getJobsByMinBudget(double minBudget) {
        return jobRepository.findByBudgetGreaterThanEqual(minBudget).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<JobDTO> getJobsSortedByBudget() {
        return jobRepository.findAllOrderByBudgetDesc().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<JobDTO> searchJobs(String keyword) {
        return jobRepository.findByTitleContainingIgnoreCase(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public JobDTO createJob(JobDTO jobDTO) {
        Job job = convertToEntity(jobDTO);
        if (job.getId() == null || job.getId().isEmpty()) {
            job.setId(UUID.randomUUID().toString().substring(0, 8));
        }
        Job savedJob = jobRepository.save(job);
        return convertToDTO(savedJob);
    }

    public JobDTO updateJob(String id, JobDTO jobDTO) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", id));

        existingJob.setTitle(jobDTO.getTitle());
        existingJob.setBudget(jobDTO.getBudget());
        existingJob.setOpen(jobDTO.isOpen());

        Job updatedJob = jobRepository.save(existingJob);
        return convertToDTO(updatedJob);
    }

    public void deleteJob(String id) {
        if (!jobRepository.existsById(id)) {
            throw new ResourceNotFoundException("Job", "id", id);
        }
        jobRepository.deleteById(id);
    }

    private JobDTO convertToDTO(Job job) {
        return new JobDTO(
                job.getId(),
                job.getTitle(),
                job.getBudget(),
                job.isOpen()
        );
    }

    private Job convertToEntity(JobDTO dto) {
        return new Job(
                dto.getId(),
                dto.getTitle(),
                dto.getBudget(),
                dto.isOpen()
        );
    }
}