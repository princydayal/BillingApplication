package com.billingapplication.service;


import com.billingapplication.model.ExpenseCategory;
import com.billingapplication.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<ExpenseCategory> findByCategoryName(String categoryName) {
        return categoryRepository.findByCategory(categoryName);
    }

    public ExpenseCategory saveCategory(ExpenseCategory category) {
        return categoryRepository.save(category);
    }

    public ExpenseCategory updateCategory(Long id, ExpenseCategory categoryDetails) {
        ExpenseCategory category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("ExpenseCategory not found"));
        category.setCategory(categoryDetails.getCategory());
        category.setDescription(categoryDetails.getDescription());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<ExpenseCategory> findAllCategories() {
        return categoryRepository.findAll();
    }
}
