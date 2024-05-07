package com.budgetSystem.budget.Controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.budgetSystem.budget.Model.Budget;
import com.budgetSystem.budget.Service.BudgetService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("api/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("/addBudget")
    public ResponseEntity<?> addBudget(@Valid @RequestBody Budget budget)
    {
        budgetService.addBudget(budget);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @GetMapping("/allBudgetsByClient")
    public ResponseEntity<?> findAllBudgetsByClientID(@RequestParam int clientID)
    {
        return ResponseEntity.ok(new SuccessResponse(budgetService.findByClientID(clientID)));
    }

    @GetMapping("/monthlyBudget")
    public ResponseEntity<?> findByMonthAndClientID(@RequestParam int month, @RequestParam int clientID)
    {

        return ResponseEntity.ok(new SuccessResponse(budgetService.findByMonthAndClientID(month, clientID)));
    }

    @DeleteMapping("/deleteBudget")
    public ResponseEntity<?> deleteBudgetByClient(@RequestParam Integer clientID, @RequestParam Integer budgetID)
    {
        List<Budget> budgets = budgetService.deleteBudgetForClient(clientID,budgetID);
        SuccessResponse successResponse = new SuccessResponse();
        if (budgets.size() == 0)successResponse.setData("");
        else successResponse.setData(budgets);
        return ResponseEntity.ok(successResponse);
    }
     

}
