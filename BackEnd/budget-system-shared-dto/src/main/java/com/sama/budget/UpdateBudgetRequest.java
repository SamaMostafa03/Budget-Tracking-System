package com.sama.budget;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class UpdateBudgetRequest {

    @NotNull(message = "budgetId cannot be null")
    @Positive(message = "budgetId cannot be negative")
    private Integer budgetId;

    @FutureOrPresent(message = "endDate must be future or current date only")
    private LocalDate endDate;

    private String budgetName;

    @Positive(message = "totalMoneyNeeded cannot be negative or zero")
    private BigDecimal totalAmount;

    @PositiveOrZero(message = "remainingAmount cannot be negative")
    private BigDecimal remainingAmount;

    private String budgetStatus;

    @Positive(message = "clientID cannot be negative")
    private Integer clientId;
}
