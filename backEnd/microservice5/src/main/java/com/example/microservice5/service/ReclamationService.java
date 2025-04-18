package com.example.microservice5.service;


import com.example.microservice5.entity.Reclamation;
import com.example.microservice5.repository.ReclamationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReclamationService {


    private ReclamationRepository reclamationRepository;

    public Reclamation saveReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    public Reclamation getReclamationById(Long id) {
        return reclamationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reclamation not found"));
    }

    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }



}
