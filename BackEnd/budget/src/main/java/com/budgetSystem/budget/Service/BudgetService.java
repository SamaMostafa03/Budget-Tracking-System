package com.budgetSystem.budget.Service;

import java.util.List;
import com.budgetSystem.budget.Model.Budget;
import com.budgetSystem.budget.Repository.BudgetRepository;
import com.budgetSystem.budget.Repository.CategoryRepository;
import com.budgetSystem.budget.dto.budget.BudgetResponse;
import com.budgetSystem.budget.dto.budget.CreateBudgetRequest;
import com.budgetSystem.budget.dto.budget.UpdateBudgetRequest;
import com.budgetSystem.budget.mapper.BudgetMapper;
import com.sama.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.budgetSystem.budget.Model.Category;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;

    public BudgetResponse addBudget(CreateBudgetRequest dto) {
        Budget budget = BudgetMapper.convertToEntity(dto);
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new RecordNotFoundException("Category not found"));
        budget.setCategory(category);
        budgetRepository.save(budget);
        return BudgetMapper.convertToResponse(budget);
    }

    public BudgetResponse updateBudget(UpdateBudgetRequest dto) {
        Budget budget = budgetRepository.findById(dto.getBudgetId()).orElseThrow(() -> new RecordNotFoundException("budget not found"));
        BudgetMapper.updateBudget(dto,budget);
        return BudgetMapper.convertToResponse(budget);
    }
    public BudgetResponse getBudgetById(Integer budgetId) {
        Budget budget = budgetRepository.findById(budgetId).orElseThrow(() -> new RecordNotFoundException("budget not found"));
        return BudgetMapper.convertToResponse(budget);
    }

    public List<BudgetResponse> getBudgetsByCategoryID(Integer categoryId) {
        List<Budget> budgets = budgetRepository.findAllByCategory_CategoryID(categoryId);
        if (budgets.isEmpty()) throw new RecordNotFoundException("No budgets made yet by the client");
        return budgets.stream().map(BudgetMapper::convertToResponse).toList();
    }

    public void deleteBudget(Integer budgetId) {
        Budget budget = budgetRepository.findById(budgetId) .orElseThrow( () -> new RecordNotFoundException("budget not found"));
        budgetRepository.delete(budget);
    }

}
