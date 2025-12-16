package com.budgetSystem.budget.mapper;

import com.budgetSystem.budget.Model.Budget;
import com.budgetSystem.budget.Model.BudgetStatus;
import com.budgetSystem.budget.Model.Category;
import com.budgetSystem.budget.dto.budget.BudgetResponse;
import com.budgetSystem.budget.dto.budget.CreateBudgetRequest;
import com.budgetSystem.budget.dto.budget.UpdateBudgetRequest;
import java.math.BigDecimal;

public class BudgetMapper {
    public static Budget convertToEntity(CreateBudgetRequest dto){
        return Budget.builder()
                .endDate(dto.getEndDate())
                .budgetName(dto.getBudgetName())
                .totalAmount(dto.getTotalAmount())
                .clientId(dto.getClientId())
                .budgetStatus(BudgetStatus.ACTIVE)
                .build();
    }

    public static void updateBudget(UpdateBudgetRequest dto, Budget budget){
        if(dto.getBudgetName()!=null)budget.setBudgetName(dto.getBudgetName());
        if(dto.getBudgetStatus()!=null)budget.setBudgetStatus(BudgetStatus.valueOf(dto.getBudgetStatus().toUpperCase()));
        if(dto.getRemainingAmount()!=null)budget.setRemainingAmount(dto.getRemainingAmount());
        if(dto.getTotalAmount()!=null)budget.setTotalAmount(dto.getTotalAmount());
        if(dto.getEndDate()!=null)budget.setEndDate(dto.getEndDate());
    }

    public static BudgetResponse convertToResponse(Budget budget){
        return BudgetResponse.builder()
                .budgetId(budget.getBudgetId())
                .clientId(budget.getClientId())
                .budgetName(budget.getBudgetName())
                .budgetStatus(budget.getBudgetStatus().toString().toLowerCase())
                .description(buildDescription(budget))
                .endDate(budget.getEndDate())
                .remainingAmount(budget.getRemainingAmount())
                .totalAmount(budget.getTotalAmount())
                .categoryId(budget.getCategory().getCategoryID())
                .spentAmount(getSpentAmount(budget))
                .build();
    }

    private static BigDecimal getSpentAmount(Budget budget){
        return budget.getTotalAmount().subtract(budget.getRemainingAmount());
    }
    private static String buildDescription(Budget budget) {
        if (budget.getRemainingAmount().compareTo(BigDecimal.ZERO) == 0) {
            return "Funded, spent " + budget.getTotalAmount();
        }
        return budget.getRemainingAmount() + " needed by " + budget.getEndDate();
    }
}
