package com.curequest.service;

import com.curequest.entity.Doctor;
import com.curequest.entity.Patient;
import com.curequest.entity.Feedback;
import com.curequest.repository.DoctorRepository;
import com.curequest.repository.PatientRepository;
import com.curequest.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public FeedbackService(
            FeedbackRepository feedbackRepository,
            DoctorRepository doctorRepository,
            PatientRepository patientRepository
    ) {
        this.feedbackRepository = feedbackRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    public Feedback addFeedback(Long doctorId, Long patientId, Feedback f) {
        Doctor d = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient p = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        f.setDoctor(d);
        f.setPatient(p);

        return feedbackRepository.save(f);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
