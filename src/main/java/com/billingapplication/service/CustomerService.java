package com.billingapplication.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.billingapplication.dto.AuthenticateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.billingapplication.model.Customer;
import com.billingapplication.repo.customerRepo;

@Service
public class CustomerService {

	@Autowired
	private customerRepo repo;
	
	private String generateTruncatedUUID() {
        UUID uuid = UUID.randomUUID();
      return uuid.toString().substring(0, 8); 
    }

    public Customer saveRecords(Customer cs) {
        if (cs.getCustomerid() == null) {
            cs.setCustomerid(generateTruncatedUUID());
        }
        return repo.save(cs);
    }
    public List<Customer> saveAllRecords(List<Customer> customers) {
        for (Customer cs : customers) {
            if (cs.getCustomerid() == null) {
                cs.setCustomerid(generateTruncatedUUID());
            }
        }
        return repo.saveAll(customers);
    }
	
	public List<Customer> getrecords(){
		return repo.findAll();
	}
	
	public Customer getCustomerById(String customerid) {
        return repo.findBycustomerid(customerid); 
    }

    public String deletecustomer(String id) { 
        repo.deleteById(id);
        return "removed";
    }

    public Customer updateCustomer(Customer customer) {
        Customer editRec = repo.findById(customer.getCustomerid()).orElse(null);
        if (editRec != null) {
            editRec.setName(customer.getName());
            editRec.setAddress(customer.getAddress());
            editRec.setEmail(customer.getEmail());
            editRec.setBalance(customer.getBalance());
            editRec.setPhonenumber(customer.getPhonenumber());
            editRec.setMessage(customer.getMessage());
            return repo.save(editRec);
        }
        return null; 
    }

    //login ------

//    public Customer registerCustomer(AuthenticateCustomer authenticateCustomer){
//        Customer customer = new Customer();
//        customer.setCustomerid(generateTruncatedUUID());
//        return repo.save(authenticateCustomer);
//    }
public Customer authenticateCustomer(AuthenticateCustomer authenticateCustomer) {
    Optional<Customer> customerOptional = repo.findByEmail(authenticateCustomer.getEmail());

    // Check if the customer exists in the database
    if (customerOptional.isPresent()) {
        Customer authCust = customerOptional.get();

        // Compare stored password with the input password
        if (authCust.getPassword().equals(authenticateCustomer.getPass())) {
            authCust.setIsAuthenticated(true);
            return authCust;
        }
        authCust.setIsAuthenticated(false);
    }

    // If authentication fails

    return null; // or throw new Exception("Authentication failed");
}

    public Customer findByEmail(String email){
        Optional<Customer> customer = repo.findByEmail(email);
        return customer.get();
    }


}