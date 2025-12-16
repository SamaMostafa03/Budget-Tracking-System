package com.BudgetTracking.UserService.Repository;

import com.BudgetTracking.UserService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String name);
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
