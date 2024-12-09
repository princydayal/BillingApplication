package com.billingapplication.repo;

import com.billingapplication.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    List<ExpenseCategory> findByCategory(String categoryName);
}
