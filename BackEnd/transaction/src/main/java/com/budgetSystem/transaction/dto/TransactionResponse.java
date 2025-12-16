package com.budgetSystem.transaction.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Builder
public class TransactionResponse {

    private Integer transactionID;

    private BigDecimal amount;

    private String note;

    private LocalDate transactionDate;

    private String type;

    private Integer budgetId;

    private Integer clientId;

    private Integer walletId;
}
