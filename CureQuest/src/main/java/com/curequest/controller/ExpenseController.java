package com.curequest.controller;

import com.curequest.entity.Expense;
import com.curequest.service.ExpenseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin("*")
public class ExpenseController {
    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) { this.expenseService = expenseService; }
    @GetMapping public List<Expense> getAll() { return expenseService.getAll(); }
    @PostMapping public Expense add(@RequestBody Expense e) { return expenseService.add(e); }
}