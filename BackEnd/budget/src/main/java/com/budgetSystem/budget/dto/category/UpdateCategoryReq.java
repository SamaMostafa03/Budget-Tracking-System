package com.budgetSystem.budget.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCategoryReq {
    @NotNull(message = "category id cannot be null")
    private Integer categoryID;

    private String categoryName;

}
