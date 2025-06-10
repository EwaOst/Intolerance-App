package com.intolerance_app.service;

import com.intolerance_app.model.IntoleranceModel;
import com.intolerance_app.repository.IntoleranceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntoleranceService {

    private final IntoleranceRepository intoleranceRepository;

    public IntoleranceService(IntoleranceRepository intoleranceRepository) {
        this.intoleranceRepository = intoleranceRepository;
    }

    public List<IntoleranceModel> getAllIntolerances() {
     return intoleranceRepository.findAll();
    }

    public Optional<IntoleranceModel> getIntoleranceById (Long id) {
        Optional<IntoleranceModel> intolerance = intoleranceRepository.findById(id);
        if (intolerance.isPresent()) {
            return intolerance;
        } else {
            throw new EntityNotFoundException("Intolerance not found");
        }
    }
}
