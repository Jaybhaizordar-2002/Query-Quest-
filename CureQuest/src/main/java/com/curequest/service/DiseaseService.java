package com.curequest.service;

import com.curequest.entity.Disease;
import com.curequest.repository.DiseaseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;

    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public List<Disease> getAll() { return diseaseRepository.findAll(); }
    public Disease add(Disease d) { return diseaseRepository.save(d); }
}