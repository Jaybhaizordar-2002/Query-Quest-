package com.curequest.controller;

import com.curequest.entity.Doctor;
import com.curequest.repository.DoctorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @PostMapping
    public Doctor add(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
