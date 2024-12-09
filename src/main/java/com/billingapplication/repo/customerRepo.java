package com.billingapplication.repo;

import com.billingapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billingapplication.model.Customer;

import java.util.Optional;

@Repository
public interface customerRepo extends JpaRepository<Customer, String> {

	Customer findBycustomerid(String customerid);
	Optional<Customer> findByName(String name);
	Optional<Customer> findByEmail(String email);

}