package com.pgfinder.user.service;

import com.pgfinder.user.entity.User;
import com.pgfinder.user.entity.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // Register a new user
    User registerUser(User user);

    // Get all users
    List<User> getAllUsers();

    // Get user by ID
    Optional<User> getUserById(Long id);

    // Get user by email
    Optional<User> getUserByEmail(String email);

    // Get user by phone
    Optional<User> getUserByPhone(String phone);

    // Get users by role
    List<User> getUsersByRole(Role role);

    // Search users by name
    List<User> searchUsersByName(String name);

    // Get all owners
    List<User> getAllOwners();

    // Get all tenants
    List<User> getAllTenants();

    // Get tenants with budget preferences
    List<User> getTenantsByBudget();

    // Update user profile
    User updateUser(Long id, User userDetails);

    // Change user role
    User changeUserRole(Long id, Role newRole);

    // Delete user
    void deleteUser(Long id);

    // Check if email exists
    boolean emailExists(String email);

    // Check if phone exists
    boolean phoneExists(String phone);

    // Count users by role
    long countUsersByRole(Role role);

}
