package com.billingapplication.service;

import com.billingapplication.model.Role;
import com.billingapplication.model.User;
import com.billingapplication.repo.RoleRepo;
import com.billingapplication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    // Find User by Id
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Check if an email is already registered
    public boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email) != null;
    }

    // Find User by username
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    // Find User by email
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    // Save a new user
    public User saveUser(User user) {
        // Check if the role exists and fetch it
        if (user.getRole() != null && user.getRole().getId() != null) {
            Optional<Role> role = roleRepository.findById(user.getRole().getId());
            role.ifPresent(user::setRole); // Set the role if found
        }
        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());  // Optionally, encode password here
            // Check if the role exists and update
            if (userDetails.getRole() != null && userDetails.getRole().getId() != null) {
                Optional<Role> role = roleRepository.findById(userDetails.getRole().getId());
                role.ifPresent(user::setRole); // Update role if found
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete user by id
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
