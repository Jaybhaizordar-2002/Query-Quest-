package com.curequest.service;

import com.curequest.entity.Expense;
import com.curequest.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAll() { return expenseRepository.findAll(); }
    public Expense add(Expense e) { return expenseRepository.save(e); }
}