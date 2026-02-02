package com.curequest.service;

import com.curequest.entity.Hospital;
import com.curequest.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> getAll() {
        return hospitalRepository.findAll();
    }

    public Hospital add(Hospital h) {
        return hospitalRepository.save(h);
    }

    // 🔥 ADD THIS METHOD — Required for delete
    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }
}
