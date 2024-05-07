package com.BudgetTracking.UserService.repository;

import com.BudgetTracking.UserService.Model.Role;
import com.BudgetTracking.UserService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository< User, Integer> {
   //User findByEmail(String email);

    boolean existsByRole(Role role);
    Optional < User > findByEmail(String email);
}


