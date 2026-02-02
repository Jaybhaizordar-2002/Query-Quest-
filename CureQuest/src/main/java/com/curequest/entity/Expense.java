package com.curequest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String expenseType;      // e.g. Consultation, Medicine, Surgery
    private double amount;           // cost amount
    private String date;             // expense date
    private String description;      // notes or details
}