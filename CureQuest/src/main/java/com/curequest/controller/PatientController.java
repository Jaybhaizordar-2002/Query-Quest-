package com.curequest.controller;

import com.curequest.entity.Patient;
import com.curequest.service.PatientService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin("*")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) { this.patientService = patientService; }

    @GetMapping public List<Patient> getAll() { return patientService.getAllPatients(); }
    @PostMapping public Patient add(@RequestBody Patient p) { return patientService.addPatient(p); }
    @GetMapping("/{id}") public Patient getById(@PathVariable Long id) { return patientService.getPatientById(id); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { patientService.deletePatient(id); }
}