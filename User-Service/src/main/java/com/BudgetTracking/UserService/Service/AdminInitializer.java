package com.BudgetTracking.UserService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.BudgetTracking.UserService.Model.Role;
import com.BudgetTracking.UserService.Model.User;
import com.BudgetTracking.UserService.repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner{
    @Autowired
    private UserRepository userRepository;
    @Autowired
     private  PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) {
        boolean isAdminExist = userRepository.existsByRole(Role.Admin);

        
        if (!isAdminExist) {
            User admin = new User();
            admin.setId(1);
            admin.setName("Admin");
            admin.setEmail("admin12@gmail.com");
            admin.setPassword(passwordEncoder.encode("Admin@123") );
            admin.setRole(Role.Admin);
            userRepository.save(admin);
        }
        
    }

}
