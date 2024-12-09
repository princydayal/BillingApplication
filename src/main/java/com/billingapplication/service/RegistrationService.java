package com.billingapplication.service;

import com.billingapplication.model.Registration;
import com.billingapplication.model.Role;
import com.billingapplication.model.User;
import com.billingapplication.repo.RegistrationRepo;
import com.billingapplication.repo.RoleRepo;
import com.billingapplication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepo registrationRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Create new registration and save user
    public Registration registerUser(Registration registration) {

        String pass = passwordEncoder.encode(registration.getPassword());
        registration.setPassword(pass);
        Registration savedRegistration = registrationRepo.save(registration);

        User newUser = new User();
        newUser.setUsername(savedRegistration.getUsername());
        newUser.setEmail(savedRegistration.getEmail());
        newUser.setPassword(pass); // Encode the password



        Role role = roleRepo.findByName(savedRegistration.getRole());
        newUser.setRole(role); // Set the user's role from the fetched Role

        userRepo.save(newUser);

        return savedRegistration;
    }

    // Get all registrations
    public List<Registration> getAllRegistrations() {
        return registrationRepo.findAll();
    }

    // Get registration by ID
    public Optional<Registration> getRegistrationById(Long id) {
        return registrationRepo.findById(id);
    }

    // Update registration by ID
    public Registration updateRegistration(Long id, Registration registrationDetails) {
        return registrationRepo.findById(id).map(registration -> {
            registration.setUsername(registrationDetails.getUsername());
            registration.setEmail(registrationDetails.getEmail());
            registration.setPassword(registrationDetails.getPassword());
            registration.setRole(registrationDetails.getRole());
            return registrationRepo.save(registration);
        }).orElseThrow(() -> new RuntimeException("Registration not found with id: " + id));
    }

    // Delete registration by ID
    public void deleteRegistration(Long id) {
        registrationRepo.deleteById(id);
    }

    // Approve registration by ID
    public Registration approveRegistration(Long id) {
        Optional<Registration> registration = registrationRepo.findById(id);
        if (registration.isPresent()) {
            Registration updatedRegistration = registration.get();
//            updatedRegistration.setApproved(true);
            return registrationRepo.save(updatedRegistration);
        } else {
            throw new RuntimeException("Registration not found");
        }
    }
}
