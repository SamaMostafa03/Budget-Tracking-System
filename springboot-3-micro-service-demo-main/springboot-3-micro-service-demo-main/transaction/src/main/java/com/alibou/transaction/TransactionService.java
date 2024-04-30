package com.alibou.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;

    public void saveTransaction(Transaction transaction) {
        repository.save(transaction);
    }

    public List<Transaction> findAllTransactions() {
        return repository.findAll();
    }

    public List<Transaction> findAllTransactionsByClient(Integer clientID) {
        return repository.findAllByClientID(clientID);
    }
    public Integer findNumberOfAllTransactionsByClient(Integer clientID) {
        List<Transaction>transactions = repository.findAllByClientID(clientID);
        return transactions.size();
    }

    public Transaction deleteTransaction(Integer transactionID) {
        Optional<Transaction> transactionOptional = repository.findById(transactionID);
        if (transactionOptional.isPresent()) {
            repository.deleteById(transactionID);
            return transactionOptional.get();
        } else {
            return null;
        }
    }

    public Transaction updateTransaction(Transaction transactionObject){
        Optional<Transaction> existingTransaction = repository.findById(transactionObject.getTransactionID());
        if (existingTransaction.isPresent()) {
            Transaction updatedTransaction = existingTransaction.get();
            updatedTransaction.setInflowAmount(transactionObject.getInflowAmount());
            updatedTransaction.setOutflowAmount(transactionObject.getOutflowAmount());
            updatedTransaction.setNote(transactionObject.getNote());
            updatedTransaction.setPayee(transactionObject.getPayee());
            updatedTransaction.setTransactionDate(transactionObject.getTransactionDate());
            updatedTransaction.setWalletType(transactionObject.getWalletType());
            updatedTransaction.setBudgetID(transactionObject.getBudgetID());
            updatedTransaction.setCategoryID(transactionObject.getCategoryID());
            updatedTransaction.setTargetID(transactionObject.getTargetID());
            updatedTransaction.setClientID(transactionObject.getClientID());
            return repository.save(updatedTransaction);
        } else {
            return null;
        }
    }


    public Transaction findTransaction(Integer TransactionID){
        Optional<Transaction> result =  repository.findById(TransactionID);
        return result.orElse(null);
    }

    public List<Transaction> filterTransactionsOfClient(Integer clientId, LocalDate startDate, LocalDate endDate,
                                                        List<String> walletTypes) {
        List<Transaction> clientTransactions = repository.findAllByClientID(clientId);
        return clientTransactions.stream()
                .filter(transaction ->
                        transaction.getTransactionDate().compareTo(startDate) >= 0 &&
                                transaction.getTransactionDate().compareTo(endDate) <= 0 &&
                                (Objects.isNull(walletTypes) || walletTypes.isEmpty() ||
                                        walletTypes.contains(transaction.getWalletType())))
                .collect(Collectors.toList());
    }
}
