package com.billingapplication.service;


import com.billingapplication.model.Product;
import com.billingapplication.model.ProductCategory;
import com.billingapplication.repo.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productrepo;
	
	private String generate6DigitCode() {
	       
        return String.format("%06d", (int) (Math.random() * 1000000));
    }

	public Product savepr(Product pr) {
	    if (pr.getProductid() == null || pr.getProductid().isEmpty()) {
	        pr.setProductid(generate6DigitCode());
	    }
	    return productrepo.save(pr);
	}


    public List<Product> saveallcb(List<Product> prList) {
        for (Product pr : prList) {
            if (pr.getProductid() == null || pr.getProductid().isEmpty()) {
                pr.setProductid(generate6DigitCode());
            }
        }
        return productrepo.saveAll(prList);
    }
    
    public List<Product> getrecords(){
    	return productrepo.findAll();
    }
    
    public Optional<Product> getproductById(String productid){
        Optional<Product> product = productrepo.findById(productid);
    	return product;
    }
    
    public String deleteproduct(String productid) {
        productrepo.deleteById(productid);
    	return "Removed";
    }
    
    public Product updateproduct(Product pr) {
    	if (pr.getProductid() == null || pr.getProductid().isEmpty()) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        Optional<Product> editRec = productrepo.findById(pr.getProductid());
        if (editRec.isPresent()) {
            Product existingPr = editRec.get();
            existingPr.setName(pr.getName());
            existingPr.setCategory(pr.getCategory());
            existingPr.setDescription(pr.getDescription());
            existingPr.setPrice(pr.getPrice());
            existingPr.setQuantity(pr.getQuantity());
            existingPr.setImage(pr.getImage());
            return productrepo.save(existingPr);
        }
        return null;
    }

    public List<Product> getByCategory(String category){
        List<Product> product = productrepo.getProductByCategory(category);
        return product;
    }
}
