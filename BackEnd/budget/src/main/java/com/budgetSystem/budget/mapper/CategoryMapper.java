package com.budgetSystem.budget.mapper;

import com.budgetSystem.budget.Model.Category;
import com.budgetSystem.budget.dto.category.CategoryResponse;
import com.budgetSystem.budget.dto.category.CreateCategoryReq;
import com.budgetSystem.budget.dto.category.UpdateCategoryReq;

public class CategoryMapper {
    public static Category convertToEntity(CreateCategoryReq dto){
        return Category.builder()
                .categoryName(dto.getCategoryName())
                .clientId(dto.getClientId())
                .build();
    }

    public static void updateCategory(UpdateCategoryReq dto, Category category){
        if(dto.getCategoryName()!=null)category.setCategoryName(dto.getCategoryName());
    }

    public static CategoryResponse convertToResponse(Category category){
        return CategoryResponse.builder()
                .categoryID(category.getCategoryID())
                .categoryName(category.getCategoryName())
                .clientId(category.getClientId())
                .build();
    }
}
