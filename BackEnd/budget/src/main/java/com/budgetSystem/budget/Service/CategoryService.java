package com.budgetSystem.budget.Service;

 
import java.util.List;
import java.util.Optional;

import com.budgetSystem.budget.exceptions.RecordNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetSystem.budget.Model.Budget;
import com.budgetSystem.budget.Model.Category;
import com.budgetSystem.budget.Model.Target;
import com.budgetSystem.budget.Repository.BudgetRepository;
import com.budgetSystem.budget.Repository.CategoryRepository;
import com.budgetSystem.budget.Repository.TargetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BudgetRepository budgetRepository;
    public void addCategory(Integer budgetId, Category category) {
         
        Optional<Budget> optionalBudget = budgetRepository.findById(budgetId);
        if (optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();
            
            // Associate the Budget with the Category
            category.setBudget(budget);
            
            // Save the Category
            categoryRepository.save(category);
        } else {
            // Handle case where the Budget with the given ID does not exist
            throw new RecordNotFoundExecption(("Budget not found"));
        }
    }


    public Category updateCategoryName(Integer clientId, Integer categoryId, String newName) {
        Category category = getCategoryById(clientId, categoryId);
        category.setCategoryName(newName);
       return categoryRepository.save(category);
    }

    public Category getCategoryById(Integer clientId,Integer categoryId)
    {
        Category category =  categoryRepository.findByClientIDAndCategoryID(clientId, categoryId)
                .orElseThrow(() -> new RecordNotFoundExecption("Category not found"));
        return category;
    }

    @Autowired
    private TargetRepository targetRepository;

    public void deleteCategory(Integer categoryId,Integer clientId)
    {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            if(category.getClientID().equals(clientId))
            {
                List<Target> targets = targetRepository.findByCategory(category);
                targetRepository.deleteAll(targets);
                categoryRepository.delete(category);
                
            }
            throw new RecordNotFoundExecption(("client has no such category id"));
        }
        throw new RecordNotFoundExecption("Category not exist");
    }
   public Integer getNumberOfAllCategoriesByClientID(Integer clientId)
    {
        List<Category> categories = categoryRepository.findAllByClientID(clientId);
        return categories.size();
    }
}
