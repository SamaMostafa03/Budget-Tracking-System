package com.budgetSystem.budget.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.budgetSystem.budget.Repository.CategoryRepository;
import com.budgetSystem.budget.exceptions.RecordNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetSystem.budget.Model.Category;
import com.budgetSystem.budget.Model.Target;
import com.budgetSystem.budget.Repository.TargetRepository;

@Service
public class TargetService {

    @Autowired
    private TargetRepository targetRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public void addTarget(String targetName, LocalDate endDate, double totalMoneyNeeded, Integer categoryId,
            Integer clientId) {
        Optional<Category> category = categoryRepository.findByClientIDAndCategoryID(clientId, categoryId);
        if (category.isPresent()) {
            Target target = new Target();
            target.setTargetName(targetName);
            target.setEndDate(endDate);
            target.setTotalMoneyNeeded(totalMoneyNeeded);
            target.setClientID(clientId);
            target.setCategory(category.get());
            targetRepository.save(target);
        } else
            throw new RecordNotFoundExecption("client has no such category id");
    }

    public Integer getNumberOfAllTargetsByClientID(Integer clientId) {
        List<Target> targets = targetRepository.findAllByClientID(clientId);
        return targets.size();
    }

    public List<Target> getTargetByClientID(Integer clientID) {
        List<Target> targets = targetRepository.findAllByClientID(clientID);
        if (targets.size() == 0)
            throw new RecordNotFoundExecption("No targets yet by the client");
        return targets;
    }

    public List <Target> deleteTarget(Integer targetId, Integer clientId) {
        Target target = targetRepository.findById(targetId).orElse(null);
        if (target != null) {
            if (target.getClientID().equals(clientId)) {
                targetRepository.delete(target);
                targetRepository.findByClientID(clientId);
                return targetRepository.findByClientID(clientId);

            } else {
                throw new RecordNotFoundExecption("Client has no such target id");
            }
        } else {
            throw new RecordNotFoundExecption("Target does not exist");
        }
    }

}
