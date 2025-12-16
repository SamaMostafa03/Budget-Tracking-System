package com.budgetSystem.transaction.dto;

import com.budgetSystem.transaction.model.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateTransactionReq {

    @NotNull(message = "Transaction Amount cannot be null")
    @PositiveOrZero(message = "Transaction Amount cannot be negative or empty")
    private BigDecimal amount;

    @NotNull(message = "note cannot be null")
    private String note;

    @NotNull(message = "Transaction Date cannot be null")
    @PastOrPresent(message = "Transaction Date cannot be future date or empty")
    private LocalDate transactionDate;

    @NotNull(message = "Transaction type cannot be null")
    private String type;

    @NotNull(message = "budgetID cannot be null")
    @Positive(message = "budgetID cannot be negative")
    private Integer budgetId;

    @NotNull(message = "clientID cannot be null")
    @Positive(message = "clientID cannot be negative")
    private Integer clientId;

    @NotNull(message = "Wallet id cannot be null")
    @Positive(message = "Wallet id cannot be negative")
    private Integer walletId;
}
