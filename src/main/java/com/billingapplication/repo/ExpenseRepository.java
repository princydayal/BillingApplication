package com.billingapplication.repo;

import com.billingapplication.model.Expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByItem_Itemname(String itemName);
    List<Expense> findByCategory_Category(String categoryName);
}
