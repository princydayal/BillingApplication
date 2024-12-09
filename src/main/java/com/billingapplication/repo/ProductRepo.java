package com.billingapplication.repo;

import com.billingapplication.model.Product;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, String> {


    //@Query("SELECT p FROM Product p WHERE p.prod = :categoryName")
    List<Product> getProductByCategory(String category);
}

