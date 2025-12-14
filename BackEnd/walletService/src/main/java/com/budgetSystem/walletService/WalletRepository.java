package com.budgetSystem.walletService;

import com.budgetSystem.walletService.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Integer> {
        List<Wallet> findAllByClientId(Integer clientId);
}
