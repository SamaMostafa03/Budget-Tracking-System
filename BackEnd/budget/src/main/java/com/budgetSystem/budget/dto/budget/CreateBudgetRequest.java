package com.budgetSystem.budget.dto.budget;

import com.budgetSystem.budget.Model.BudgetStatus;
import com.budgetSystem.budget.Model.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateBudgetRequest {

    @NotNull(message = "endDate cannot be null")
    @FutureOrPresent(message = "endDate must be future or current date only")
    private LocalDate endDate;

    @NotEmpty(message = "budget Name is mandatory")
    private String budgetName;

    @NotNull(message = "totalMoneyNeeded cannot be null")
    @Positive(message = "totalMoneyNeeded cannot be negative or zero")
    private BigDecimal totalAmount;

    @NotNull(message = "clientId cannot be null")
    @Positive(message = "clientId cannot be negative")
    private Integer clientId;

    @NotNull(message = "categoryId cannot be null")
    @Positive(message = "categoryId cannot be negative")
    private Integer categoryId;
}
