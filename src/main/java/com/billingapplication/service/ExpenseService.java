package com.billingapplication.service;

import com.billingapplication.model.Expense;
import com.billingapplication.repo.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> findByItemName(String itemName) {
        return expenseRepository.findByItem_Itemname(itemName);
    }

    public List<Expense> findByCategoryName(String categoryName) {
        return expenseRepository.findByCategory_Category(categoryName);
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        expense.setItem(expenseDetails.getItem());
        expense.setCategory(expenseDetails.getCategory());
        expense.setQuantity(expenseDetails.getQuantity());
        expense.setDate(expenseDetails.getDate());
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> findAllExpenses() {
        return expenseRepository.findAll();
    }
}
