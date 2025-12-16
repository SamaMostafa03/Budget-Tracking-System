package com.budgetSystem.transaction;

import com.budgetSystem.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByClientId(Integer clientId);
    List<Transaction> findByClientIdAndWalletIdAndTransactionDateBetween(
            Integer clientId, List<Integer> walletId,
            LocalDate starDate, LocalDate endDate);

}
