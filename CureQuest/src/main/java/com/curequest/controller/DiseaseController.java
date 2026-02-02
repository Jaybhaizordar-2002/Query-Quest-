package com.curequest.controller;

import com.curequest.entity.Disease;
import com.curequest.service.DiseaseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/diseases")
@CrossOrigin("*")
public class DiseaseController {
    private final DiseaseService diseaseService;
    public DiseaseController(DiseaseService diseaseService) { this.diseaseService = diseaseService; }
    @GetMapping public List<Disease> getAll() { return diseaseService.getAll(); }
    @PostMapping public Disease add(@RequestBody Disease d) { return diseaseService.add(d); }
}