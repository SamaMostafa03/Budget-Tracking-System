package com.budgetSystem.budget.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.sama.exceptions.RecordNotFoundExecption;
import org.springframework.stereotype.Service;
 

import com.budgetSystem.budget.Model.Budget;
import com.budgetSystem.budget.Repository.BudgetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public void addBudget(Budget budget) {
         budgetRepository.save(budget);
    }

    public List<Budget> findByClientID(int clientID)
    {
        List<Budget> budgets = budgetRepository.findByClientID(clientID);
        if (budgets.size() == 0) throw new RecordNotFoundExecption("No budgets yet by the client");
        return budgets;
    }

    public List<Budget> findByMonthAndClientID(int month, int clientID)
    {
        if(month >= 1 && month <= 12)
        {
            List<Budget> allBudgets = budgetRepository.findByClientID(clientID);
            if(allBudgets.size()==0)throw new RecordNotFoundExecption("Client has no budgets yet");
            List<Budget> filteredBudgets =  allBudgets.stream()
                    .filter(budget -> getMonthFromBudgetPeriod(budget.getBudgetPeriod()) == month)
                    .collect(Collectors.toList());
            if(filteredBudgets.size()>=1)return filteredBudgets;
            throw new RecordNotFoundExecption("No Budget exist in that month");
        }
        throw new RecordNotFoundExecption("Month must be between 1 and 12");
    }

    private int getMonthFromBudgetPeriod(LocalDate budgetPeriod) {
        return budgetPeriod.getMonthValue();
    }

    public List<Budget> deleteBudgetForClient(Integer clientId,Integer budgetId) {
        Optional<Budget> budget = budgetRepository.findById(budgetId);
        if(budget.isPresent())
        {
            if(budget.get().getClientID().equals(clientId))
            {
                budgetRepository.deleteById(budgetId);
                List<Budget> budgets = budgetRepository.findByClientID(clientId);
                return budgets;
            }
            throw new RecordNotFoundExecption("client has no such budget id");
        }
        throw new RecordNotFoundExecption("budget not exist");
    }

     

}
