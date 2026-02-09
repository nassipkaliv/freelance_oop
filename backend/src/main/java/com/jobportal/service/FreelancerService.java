package com.jobportal.service;

import com.jobportal.dto.FreelancerDTO;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.model.Freelancer;
import com.jobportal.repository.FreelancerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class FreelancerService {

    private final FreelancerRepository freelancerRepository;

    public FreelancerService(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    public List<FreelancerDTO> getAllFreelancers() {
        return freelancerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FreelancerDTO getFreelancerById(String id) {
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", "id", id));
        return convertToDTO(freelancer);
    }

    public List<FreelancerDTO> getFreelancersBySkill(String skill) {
        return freelancerRepository.findBySkill(skill).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FreelancerDTO> getFreelancersSortedBySalary() {
        return freelancerRepository.findAllOrderBySalaryDesc().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FreelancerDTO> searchFreelancers(String name) {
        return freelancerRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FreelancerDTO createFreelancer(FreelancerDTO freelancerDTO) {
        Freelancer freelancer = convertToEntity(freelancerDTO);
        if (freelancer.getId() == null || freelancer.getId().isEmpty()) {
            freelancer.setId(UUID.randomUUID().toString().substring(0, 8));
        }
        Freelancer savedFreelancer = freelancerRepository.save(freelancer);
        return convertToDTO(savedFreelancer);
    }

    public FreelancerDTO updateFreelancer(String id, FreelancerDTO freelancerDTO) {
        Freelancer existingFreelancer = freelancerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", "id", id));

        existingFreelancer.setName(freelancerDTO.getName());
        existingFreelancer.setEmail(freelancerDTO.getEmail());
        existingFreelancer.setSkills(freelancerDTO.getSkills());
        existingFreelancer.setSalary(freelancerDTO.getSalary());

        Freelancer updatedFreelancer = freelancerRepository.save(existingFreelancer);
        return convertToDTO(updatedFreelancer);
    }

    public void deleteFreelancer(String id) {
        if (!freelancerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Freelancer", "id", id);
        }
        freelancerRepository.deleteById(id);
    }

    private FreelancerDTO convertToDTO(Freelancer freelancer) {
        return new FreelancerDTO(
                freelancer.getId(),
                freelancer.getName(),
                freelancer.getEmail(),
                freelancer.getSkills(),
                freelancer.getSalary()
        );
    }

    private Freelancer convertToEntity(FreelancerDTO dto) {
        return new Freelancer(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getSkills(),
                dto.getSalary()
        );
    }
}