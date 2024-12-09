package com.billingapplication.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billingapplication.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	 User findByUsername(String username);
	 User findByEmail(String email);
}
