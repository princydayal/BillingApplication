package com.billingapplication.service;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.billingapplication.model.ProductCategory;
import com.billingapplication.repo.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProductCategoryService {
    
    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    public ProductCategory saveCategory(ProductCategory productCategory){
        String categoryId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
        productCategory.setCategoryId("prca"+categoryId);
       return productCategoryRepo.save(productCategory);
    }

    public List<ProductCategory> getAllCategory(){
        List<ProductCategory> prCategoryList = new LinkedList<>();
        prCategoryList.addAll(productCategoryRepo.findAll());
        return prCategoryList;
    }

    public Optional<ProductCategory> getCategory(String id){
       //Optional<ProductCategory> prCategory = productCategoryRepo.findById(id);
        return productCategoryRepo.findById(id);
    }

    public ProductCategory updateCategory(ProductCategory productCategory){
        if(productCategory.getCategoryId()==null){
            throw new IllegalArgumentException("the id must not be null");
        }
        Optional<ProductCategory> editCategory = productCategoryRepo.findById(productCategory.getCategoryId());
        if(editCategory.isPresent()){
        ProductCategory existingCategory = editCategory.get();
        existingCategory.setCategory_name(productCategory.getCategory_name());
        existingCategory.setCategory_description(productCategory.getCategory_description());
        return productCategoryRepo.save(existingCategory);
        }
        return null;
    }
    
    public String delCategory(String id){
        Optional<ProductCategory> isExist = productCategoryRepo.findById(id);
        if(!isExist.isPresent()){
            return "Category not found";
        }
        productCategoryRepo.deleteById(id);
        return "Category deleted successfully";
    }

    public List<ProductCategory> saveAllPrCategory(List<ProductCategory> prCategory) {
        if (prCategory != null && !prCategory.isEmpty()) {
            for (ProductCategory productCategory : prCategory) {
                // Generate a unique ID for each product category
                String categoryId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
                productCategory.setCategoryId(categoryId);
            }
            // Save all categories and return the saved list
            return productCategoryRepo.saveAll(prCategory);
        }
        return Collections.emptyList(); // Return an empty list instead of null
    }
    
}
