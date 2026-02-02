package com.curequest.controller;

import com.curequest.entity.Hospital;
import com.curequest.service.HospitalService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@CrossOrigin("*")
public class HospitalController {
    private final HospitalService hospitalService;
    public HospitalController(HospitalService hospitalService) { this.hospitalService = hospitalService; }
    @GetMapping public List<Hospital> getAll() { return hospitalService.getAll(); }
    @PostMapping public Hospital add(@RequestBody Hospital h) { return hospitalService.add(h); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { hospitalService.deleteHospital(id); }
}