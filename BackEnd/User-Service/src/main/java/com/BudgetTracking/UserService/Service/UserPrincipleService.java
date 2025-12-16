package com.BudgetTracking.UserService.Service;

import com.BudgetTracking.UserService.Model.User;
import com.BudgetTracking.UserService.Model.UserPrinciple;
import com.BudgetTracking.UserService.Repository.UserRepo;
import com.sama.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPrincipleService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws RecordNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow( ()-> new RecordNotFoundException("User not found"));
        return new UserPrinciple(user);
    }
}

