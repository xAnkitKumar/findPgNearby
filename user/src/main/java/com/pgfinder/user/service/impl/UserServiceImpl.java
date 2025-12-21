package com.pgfinder.user.service.impl;

import com.pgfinder.user.entity.User;
import com.pgfinder.user.entity.Role;
import com.pgfinder.user.repository.UserRepository;
import com.pgfinder.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        // Check if email already exists
        if (emailExists(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        // Check if phone already exists
        if (phoneExists(user.getPhone())) {
            throw new RuntimeException("Phone already registered");
        }
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<User> searchUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<User> getAllOwners() {
        return userRepository.findByRole(Role.OWNER);
    }

    @Override
    public List<User> getAllTenants() {
        return userRepository.findByRole(Role.TENANT);
    }

    @Override
    public List<User> getTenantsByBudget() {
        return userRepository.findByRoleAndBudgetIsNotNull(Role.TENANT);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (userDetails.getName() != null) {
                user.setName(userDetails.getName());
            }
            if (userDetails.getEmail() != null && !userDetails.getEmail().equals(user.getEmail())) {
                if (emailExists(userDetails.getEmail())) {
                    throw new RuntimeException("Email already in use");
                }
                user.setEmail(userDetails.getEmail());
            }
            if (userDetails.getPhone() != null && !userDetails.getPhone().equals(user.getPhone())) {
                if (phoneExists(userDetails.getPhone())) {
                    throw new RuntimeException("Phone already in use");
                }
                user.setPhone(userDetails.getPhone());
            }
            if (userDetails.getPreferredLocation() != null) {
                user.setPreferredLocation(userDetails.getPreferredLocation());
            }
            if (userDetails.getBudget() != null) {
                user.setBudget(userDetails.getBudget());
            }
            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Override
    public User changeUserRole(Long id, Role newRole) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(newRole);
            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean phoneExists(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public long countUsersByRole(Role role) {
        return userRepository.findByRole(role).size();
    }

}
