package com.billingapplication.controller;

import com.billingapplication.model.Product;
import com.billingapplication.model.ProductCategory;
import com.billingapplication.service.ProductCategoryService;
import com.billingapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @Autowired
    private ProductCategoryService productCategoryService;

    //Category Routes
    @PostMapping("/category/addProductCategory")
    public ResponseEntity<ProductCategory> addCategory(@RequestBody ProductCategory productCategory) {
        ProductCategory prCategory = productCategoryService.saveCategory(productCategory);
        return new ResponseEntity<>(prCategory, HttpStatus.OK);
    }

    @PostMapping("/category/addAllProductCategory")
    public ResponseEntity<List<ProductCategory>> addAllCategory(@RequestBody List<ProductCategory> prAllCategory){
        return new ResponseEntity<>(productCategoryService.saveAllPrCategory(prAllCategory),HttpStatus.OK);
    }

    @GetMapping("/category/getAllCategories")
    public ResponseEntity<List<ProductCategory>> fetchAllProductCategory() {
        List<ProductCategory> categoriesList = productCategoryService.getAllCategory();
        if (!categoriesList.isEmpty()) {
            return new ResponseEntity<>(categoriesList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category/getCategory/{categoryId}")
    public ResponseEntity<ProductCategory> fetchCategory(@PathVariable String categoryId){
        Optional<ProductCategory> category = productCategoryService.getCategory(categoryId);
        if(category.isPresent()){
            return new ResponseEntity<>(category.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/category/updateCategory")
    public ResponseEntity<ProductCategory> updatePrCategory(@RequestBody ProductCategory prCategory) {
        try {
            if (prCategory != null) {
                ProductCategory updatedPrCategory = productCategoryService.updateCategory(prCategory);
                return new ResponseEntity<>(updatedPrCategory, HttpStatus.OK);
            }
        } catch (ConfigDataResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/category/deleteCategory/{id}")
    public ResponseEntity<String> delPrCategory(@PathVariable String id){
        String msg = productCategoryService.delCategory(id);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }


    @PostMapping("/addpr")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.savepr(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PostMapping("/addallpr")
    public ResponseEntity<List<Product>> addMultipleProducts(@RequestBody List<Product> products) {
        List<Product> savedProducts = productService.saveallcb(products);
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = productService.getrecords();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productid}")
    public ResponseEntity<Product> findProductById(@PathVariable String productid) {
        Optional<Product> product = productService.getproductById(productid);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateproduct(product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getbycategory/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category){


        List<Product> products = productService.getByCategory(category);
        if(!products.isEmpty()){
            return  new ResponseEntity<>(products,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{productid}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productid) {
        try {
            String result = productService.deleteproduct(productid);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("An error occured while deleting a product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
