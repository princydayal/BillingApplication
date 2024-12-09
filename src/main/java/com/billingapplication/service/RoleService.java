package com.billingapplication.service;

import com.billingapplication.model.Role;
import com.billingapplication.repo.RoleRepo;
import com.billingapplication.model.Erole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepository;

    // Find Role by Id
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    // Find Role by Erole name
    public Optional<Role> findByName(Erole name) {
        Role role = roleRepository.findByName(name);
        return Optional.ofNullable(role);  // Wrap result in Optional to handle null values
    }

    // Save a new role
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    // Update an existing role
    public Role updateRole(Long id, Role roleDetails) {
        return roleRepository.findById(id).map(role -> {
            role.setName(roleDetails.getName());
            return roleRepository.save(role);
        }).orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    // Retrieve all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Delete role by id
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
