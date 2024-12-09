package com.billingapplication.repo;

import com.billingapplication.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;



public interface  ProductCategoryRepo extends JpaRepository<ProductCategory, String> {
    
}
