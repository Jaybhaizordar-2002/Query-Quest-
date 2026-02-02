package com.curequest.controller;

import com.curequest.entity.Feedback;
import com.curequest.service.FeedbackService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin("*")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<Feedback> getAll() {
        return feedbackService.getAll();
    }

    @PostMapping
    public Feedback add(
            @RequestParam Long doctorId,
            @RequestParam Long patientId,
            @RequestBody Feedback f
    ) {
        return feedbackService.addFeedback(doctorId, patientId, f);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }
}
