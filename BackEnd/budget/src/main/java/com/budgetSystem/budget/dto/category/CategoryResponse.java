package com.budgetSystem.budget.dto.category;

import com.budgetSystem.budget.Model.Budget;
import com.budgetSystem.budget.dto.budget.BudgetResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryResponse {
    private Integer categoryID;

    private String categoryName;

    private Integer clientId;

//    private List<BudgetResponse> budgets;
}
