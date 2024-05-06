package com.budgetSystem.budget.Controller;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.budgetSystem.budget.Model.Budget;
import com.budgetSystem.budget.Service.BudgetService;

import lombok.RequiredArgsConstructor;

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

    @GetMapping("/monthlyBudget")
    public ResponseEntity<?> findByMonthAndClientID(@RequestParam int month, @RequestParam int clientID)
    {
        return ResponseEntity.ok(new SuccessResponse(budgetService.findByMonthAndClientID(month, clientID)));
    }

    @GetMapping("/deleteBudget")
    public ResponseEntity<?> deleteBudgetByClient(@RequestParam Integer clientID, @RequestParam Integer budgetID)
    {
        budgetService.deleteBudgetForClient(budgetID,clientID);
        return ResponseEntity.ok(new SuccessResponse());
    }
     

}
