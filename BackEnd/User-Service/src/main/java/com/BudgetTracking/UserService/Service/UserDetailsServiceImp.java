package com.BudgetTracking.UserService.Service;

import com.sama.exceptions.RecordNotFoundExecption;
import com.BudgetTracking.UserService.repository.UserRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
 
import org.springframework.stereotype.Service;

 

@ComponentScan(basePackages = {"com.BudgetTracking.UserService.Service", "com.BudgetTracking.UserService.repository"})
@ComponentScan(basePackages = "com.BudgetTracking.UserService.Controller")
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return repository.findByEmail(email).orElseThrow(()-> new RecordNotFoundExecption("User not found"));
    }
}
