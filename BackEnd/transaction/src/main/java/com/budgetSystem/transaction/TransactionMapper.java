package com.budgetSystem.transaction;

import com.budgetSystem.transaction.dto.CreateTransactionReq;
import com.budgetSystem.transaction.dto.TransactionResponse;
import com.budgetSystem.transaction.model.Transaction;
import com.budgetSystem.transaction.model.TransactionType;

public class TransactionMapper {
    public static Transaction convertToEntity(CreateTransactionReq dto){
        return Transaction.builder()
                .amount(dto.getAmount())
                .type(TransactionType.valueOf(dto.getType().toUpperCase()))
                .note(dto.getNote())
                .transactionDate(dto.getTransactionDate())
                .walletId(dto.getWalletId())
                .clientId(dto.getClientId())
                .budgetId(dto.getBudgetId())
                .build();
    }

    public static TransactionResponse convertToResponce(Transaction transaction){
        return TransactionResponse.builder()
                .transactionID(transaction.getTransactionID())
                .amount(transaction.getAmount())
                .budgetId(transaction.getBudgetId())
                .transactionDate(transaction.getTransactionDate())
                .walletId(transaction.getWalletId())
                .note(transaction.getNote())
                .clientId(transaction.getClientId())
                .type(transaction.getType().toString())
                .build();
    }
}
