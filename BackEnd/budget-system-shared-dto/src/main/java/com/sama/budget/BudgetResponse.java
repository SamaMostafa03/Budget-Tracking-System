package com.sama.budget;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class BudgetResponse {

    private Integer budgetId;

    private LocalDate endDate;

    private String budgetName;

    private BigDecimal totalAmount;

    private BigDecimal remainingAmount;

    private BigDecimal spentAmount;

    private String budgetStatus;

    private String description;

    private Integer clientId;

    private Integer categoryId;
}
