package com.budgetSystem.budget.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.budgetSystem.budget.Repository.CategoryRepository;
import com.budgetSystem.budget.exceptions.RecordNotFoundExecption;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetSystem.budget.Model.Category;
import com.budgetSystem.budget.Model.Target;
import com.budgetSystem.budget.Repository.TargetRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class TargetService {

 @Autowired
private TargetRepository targetRepository;
 @Autowired
 private CategoryRepository categoryRepository;

public void addTarget(String targetName,LocalDate endDate,double totalMoneyNeeded,Integer categoryId,Integer clientId)
{
    Optional<Category> category = categoryRepository.findByClientIDAndCategoryID(clientId,categoryId);
    if(category.isPresent())
    {
        Target target = new Target();
        target.setTargetName(targetName);
        target.setEndDate(endDate);
        target.setTotalMoneyNeeded(totalMoneyNeeded);
        target.setClientID(clientId);
        target.setCategory(category.get());
        targetRepository.save(target);
    }
    throw new RecordNotFoundExecption("client has no such category id");
}
   public Integer getNumberOfAllTargetsByClientID(Integer clientId)
    {
        List<Target> targets = targetRepository.findAllByClientID(clientId);
        return targets.size();
    }

}
