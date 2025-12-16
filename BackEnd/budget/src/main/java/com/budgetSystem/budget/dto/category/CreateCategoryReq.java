package com.budgetSystem.budget.dto.category;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateCategoryReq {

    @NotEmpty(message = "categoryName is mandatory")
    private String categoryName;

    @NotNull(message = "client Id cannot be null")
    @Positive(message = "client Id cannot be negative or zero")
    private Integer clientId;
}
