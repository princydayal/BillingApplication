package com.billingapplication.controller;

import com.billingapplication.dto.AuthenticateCustomer;
import com.billingapplication.model.Customer;
import com.billingapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("api/customer")

public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = service.saveRecords(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/multicust")
    public ResponseEntity<List<Customer>> addMultipleCustomers(@RequestBody List<Customer> customers) {
        List<Customer> savedCustomers = service.saveAllRecords(customers);
        return new ResponseEntity<>(savedCustomers, HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> findAllCustomers() {
        List<Customer> customers = service.getrecords();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerid}")
    public ResponseEntity<Customer> findCustomerByCustomerId(@PathVariable String customerid) {
        Customer customer = service.getCustomerById(customerid);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer updatedCustomer = service.updateCustomer(customer);
        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        try {
            String result = service.deletecustomer(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> customerLogin(@RequestBody AuthenticateCustomer authenticateCustomer) {
        Customer customer = service.authenticateCustomer(authenticateCustomer);

        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.ACCEPTED); // Successfully authenticated
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Authentication failed
        }
    }

    @GetMapping("/byemail/{email}")
    public ResponseEntity<Customer> findByEmail(@PathVariable String email){
        return new ResponseEntity<>(service.findByEmail(email),HttpStatus.OK);
    }

}