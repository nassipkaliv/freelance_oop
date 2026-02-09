package com.jobportal.controller;

import com.jobportal.dto.FreelancerDTO;
import com.jobportal.service.FreelancerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freelancers")
public class FreelancerController {

    private final FreelancerService freelancerService;

    public FreelancerController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    @GetMapping
    public ResponseEntity<List<FreelancerDTO>> getAllFreelancers() {
        List<FreelancerDTO> freelancers = freelancerService.getAllFreelancers();
        return ResponseEntity.ok(freelancers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FreelancerDTO> getFreelancerById(@PathVariable String id) {
        FreelancerDTO freelancer = freelancerService.getFreelancerById(id);
        return ResponseEntity.ok(freelancer);
    }

    @GetMapping("/skill/{skill}")
    public ResponseEntity<List<FreelancerDTO>> getFreelancersBySkill(@PathVariable String skill) {
        List<FreelancerDTO> freelancers = freelancerService.getFreelancersBySkill(skill);
        return ResponseEntity.ok(freelancers);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<FreelancerDTO>> getFreelancersSortedBySalary() {
        List<FreelancerDTO> freelancers = freelancerService.getFreelancersSortedBySalary();
        return ResponseEntity.ok(freelancers);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FreelancerDTO>> searchFreelancers(@RequestParam String name) {
        List<FreelancerDTO> freelancers = freelancerService.searchFreelancers(name);
        return ResponseEntity.ok(freelancers);
    }

    @PostMapping
    public ResponseEntity<FreelancerDTO> createFreelancer(@Valid @RequestBody FreelancerDTO freelancerDTO) {
        FreelancerDTO createdFreelancer = freelancerService.createFreelancer(freelancerDTO);
        return new ResponseEntity<>(createdFreelancer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FreelancerDTO> updateFreelancer(@PathVariable String id, @Valid @RequestBody FreelancerDTO freelancerDTO) {
        FreelancerDTO updatedFreelancer = freelancerService.updateFreelancer(id, freelancerDTO);
        return ResponseEntity.ok(updatedFreelancer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFreelancer(@PathVariable String id) {
        freelancerService.deleteFreelancer(id);
        return ResponseEntity.noContent().build();
    }
}