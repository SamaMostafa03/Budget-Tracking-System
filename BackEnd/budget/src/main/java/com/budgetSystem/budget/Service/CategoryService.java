package com.budgetSystem.budget.Service;

 
import java.util.List;
import java.util.Optional;

import com.budgetSystem.budget.dto.category.CategoryResponse;
import com.budgetSystem.budget.dto.category.CreateCategoryReq;
import com.budgetSystem.budget.dto.category.UpdateCategoryReq;
import com.budgetSystem.budget.mapper.CategoryMapper;
import com.sama.exceptions.RecordNotFoundException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetSystem.budget.Model.Budget;
import com.budgetSystem.budget.Model.Category;
import com.budgetSystem.budget.Repository.BudgetRepository;
import com.budgetSystem.budget.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BudgetRepository budgetRepository;

    public CategoryResponse addCategory(CreateCategoryReq dto) {
        Category category = CategoryMapper.convertToEntity(dto);
        categoryRepository.save(category);
        return CategoryMapper.convertToResponse(category);
    }


    public CategoryResponse updateCategoryName(UpdateCategoryReq dto) {
        Category category = categoryRepository.findById(dto.getCategoryID()).orElseThrow(() -> new RecordNotFoundException("Category not found"));
        CategoryMapper.updateCategory(dto, category);
       return CategoryMapper.convertToResponse(category);
    }

    public CategoryResponse getCategoryById(Integer categoryId)
    {
        Category category =  categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RecordNotFoundException("Category not found"));
        return CategoryMapper.convertToResponse(category);
    }

    public void deleteCategory(Integer clientId)
    {
        Category category = categoryRepository.findById(clientId).orElseThrow(() -> new RecordNotFoundException("Category not found"));
        List<Budget> budgets = budgetRepository.findAllByCategory_CategoryID(category.getCategoryID());
        budgetRepository.deleteAll(budgets);
        categoryRepository.delete(category);
    }
   public List<CategoryResponse> getAllCategoriesByClient(Integer clientId)
    {
        List<Category> categories = categoryRepository.findAllByClientId(clientId);
        return categories.stream()
                .map(CategoryMapper::convertToResponse)
                .toList();
    }
}
