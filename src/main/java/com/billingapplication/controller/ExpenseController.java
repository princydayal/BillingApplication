package com.billingapplication.controller;

import com.billingapplication.model.Expense;
import com.billingapplication.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/item/{itemName}")
    public ResponseEntity<List<Expense>> getExpensesByItemName(@PathVariable String itemName) {
        List<Expense> expenses = expenseService.findByItemName(itemName);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Expense>> getExpensesByCategoryName(@PathVariable String categoryName) {
        List<Expense> expenses = expenseService.findByCategoryName(categoryName);
        return ResponseEntity.ok(expenses);
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense savedExpense = expenseService.saveExpense(expense);
        return ResponseEntity.ok(savedExpense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        Expense updatedExpense = expenseService.updateExpense(id, expenseDetails);
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.findAllExpenses();
        return ResponseEntity.ok(expenses);
    }
}
