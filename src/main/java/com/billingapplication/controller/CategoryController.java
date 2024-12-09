package com.billingapplication.controller;

import com.billingapplication.model.ExpenseCategory;
import com.billingapplication.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<List<ExpenseCategory>> getCategoriesByName(@PathVariable String categoryName) {
        List<ExpenseCategory> categories = categoryService.findByCategoryName(categoryName);
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<ExpenseCategory> createCategory(@RequestBody ExpenseCategory ExpenseCategory) {
        ExpenseCategory savedCategory = categoryService.saveCategory(ExpenseCategory);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseCategory> updateCategory(@PathVariable Long id, @RequestBody ExpenseCategory categoryDetails) {
        ExpenseCategory updatedCategory = categoryService.updateCategory(id, categoryDetails);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ExpenseCategory>> getAllCategories() {
        List<ExpenseCategory> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }
}
