package com.budgetSystem.budget.Service;

 
import java.util.List;

import com.budgetSystem.budget.exceptions.RecordNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.budgetSystem.budget.Model.Category;
import com.budgetSystem.budget.Model.Target;
import com.budgetSystem.budget.Repository.CategoryRepository;
import com.budgetSystem.budget.Repository.TargetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(Category category)
    {
         categoryRepository.save(category);
    }

    public void updateCategoryName(Integer clientId, Integer categoryId, String newName) {
        Category category = getCategoryById(clientId, categoryId);
        category.setCategoryName(newName);
        categoryRepository.save(category);
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
