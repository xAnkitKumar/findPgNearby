package com.pgfinder.user.repository;

import com.pgfinder.user.entity.User;
import com.pgfinder.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find by email
    Optional<User> findByEmail(String email);

    // Find by phone
    Optional<User> findByPhone(String phone);

    // Find by role
    List<User> findByRole(Role role);

    // Find by name (contains)
    List<User> findByNameContainingIgnoreCase(String name);

    // Check if email exists
    boolean existsByEmail(String email);

    // Check if phone exists
    boolean existsByPhone(String phone);

    // Find tenants with budget
    List<User> findByRoleAndBudgetIsNotNull(Role role);

}
