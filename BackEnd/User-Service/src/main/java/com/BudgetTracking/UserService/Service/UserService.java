package com.BudgetTracking.UserService.Service;

import com.BudgetTracking.UserService.Model.AuthProvider;
import com.BudgetTracking.UserService.Model.Role;
import com.BudgetTracking.UserService.Model.User;
import com.BudgetTracking.UserService.Repository.UserRepo;
import com.sama.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    public User save(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setProvider(AuthProvider.LOCAL);
            if(user.getRole()==null)user.setRole(Role.USER);
        }
        return userRepo.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow( () -> new RecordNotFoundException("User does not exist"));
    }
}
