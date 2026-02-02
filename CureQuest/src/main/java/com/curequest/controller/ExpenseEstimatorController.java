package com.curequest.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/estimator")
@CrossOrigin("*")
public class ExpenseEstimatorController {

    // realistic base values
    private static final double CONSULTATION_GENERAL = 500;
    private static final double CONSULTATION_SPECIALIST = 1000;
    private static final double MEDICINE = 1500;
    private static final double TESTS = 2500;
    private static final double SURGERY_MINOR = 20000;
    private static final double SURGERY_MAJOR = 70000;
    private static final double BED_GOVT = 200;
    private static final double BED_PRIVATE = 1500;
    private static final double BED_PRIVATE_ROOM = 3000;
    private static final double BED_DELUXE = 5000;
    private static final double BED_ICU = 10000;

    @PostMapping("/calculate")
    public Map<String, Object> calculate(@RequestBody Map<String, Object> req) {
        String treatmentType = (String) req.getOrDefault("treatmentType", "general"); // general, medication, diagnostics, surgery_minor, surgery_major
        String hospitalType = (String) req.getOrDefault("hospitalType", "private_room"); // govt, private, private_room, deluxe, icu
        int days = ((Number) req.getOrDefault("daysAdmitted", 0)).intValue();
        boolean meds = (boolean) req.getOrDefault("medicationRequired", false);
        boolean tests = (boolean) req.getOrDefault("testsRequired", false);

        double consultation = CONSULTATION_GENERAL;
        if ("specialist".equalsIgnoreCase((String)req.getOrDefault("doctorType","general"))) consultation = CONSULTATION_SPECIALIST;

        double medicineCost = meds ? MEDICINE : 0;
        double testCost = tests ? TESTS : 0;
        double surgeryCost = 0;
        if ("surgery_major".equalsIgnoreCase(treatmentType)) surgeryCost = SURGERY_MAJOR;
        if ("surgery_minor".equalsIgnoreCase(treatmentType)) surgeryCost = SURGERY_MINOR;

        double bedPerDay = BED_PRIVATE_ROOM;
        if ("govt".equalsIgnoreCase(hospitalType)) bedPerDay = BED_GOVT;
        if ("private".equalsIgnoreCase(hospitalType)) bedPerDay = BED_PRIVATE;
        if ("private_room".equalsIgnoreCase(hospitalType)) bedPerDay = BED_PRIVATE_ROOM;
        if ("deluxe".equalsIgnoreCase(hospitalType)) bedPerDay = BED_DELUXE;
        if ("icu".equalsIgnoreCase(hospitalType)) bedPerDay = BED_ICU;

        double bedCharges = days * bedPerDay;
        double gross = consultation + medicineCost + testCost + surgeryCost + bedCharges;

        // apply discount/scheme if provided
        double discountFactor = 1.0;
        if (req.containsKey("discountPercentage")) {
            double perc = ((Number)req.get("discountPercentage")).doubleValue();
            discountFactor = Math.max(0.0, (100.0 - perc) / 100.0);
        }

        double finalPayable = gross * discountFactor;

        Map<String,Object> resp = new HashMap<>();
        resp.put("consultation", consultation);
        resp.put("medicine", medicineCost);
        resp.put("tests", testCost);
        resp.put("surgery", surgeryCost);
        resp.put("bedCharges", bedCharges);
        resp.put("grossBill", gross);
        resp.put("finalPayable", finalPayable);
        resp.put("currency","INR");
        return resp;
    }
}