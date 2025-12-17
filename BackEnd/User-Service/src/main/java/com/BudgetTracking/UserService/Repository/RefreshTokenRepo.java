package com.BudgetTracking.UserService.Repository;


import com.BudgetTracking.UserService.Model.RefreshToken;
import com.BudgetTracking.UserService.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByToken(String token);
    @Transactional
    void deleteByUser(User user);

}
