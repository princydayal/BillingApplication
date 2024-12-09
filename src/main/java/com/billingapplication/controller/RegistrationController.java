package com.billingapplication.controller;

import com.billingapplication.model.Registration;
import com.billingapplication.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    
    @PostMapping("/register")
    public ResponseEntity<Registration> registerUser(@RequestBody Registration registration) {
        Registration savedRegistration = registrationService.registerUser(registration);
        return ResponseEntity.ok(savedRegistration);
    }

    
    @GetMapping
    public ResponseEntity<List<Registration>> getAllRegistrations() {
        List<Registration> registrations = registrationService.getAllRegistrations();
        return ResponseEntity.ok(registrations);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Registration>> getRegistrationById(@PathVariable Long id) {
        Optional<Registration> registration = registrationService.getRegistrationById(id);
        return ResponseEntity.ok(registration);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable Long id, @RequestBody Registration registrationDetails) {
        Registration updatedRegistration = registrationService.updateRegistration(id, registrationDetails);
        return ResponseEntity.ok(updatedRegistration);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }

//    // Approve a registration by ID
//    @PostMapping("/approve/{id}")
//    public ResponseEntity<Registration> approveRegistration(@PathVariable Long id) {
//        Registration approvedRegistration = registrationService.approveRegistration(id);
//        return ResponseEntity.ok(approvedRegistration);
//    }
}
