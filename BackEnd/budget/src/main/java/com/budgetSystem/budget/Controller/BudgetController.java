package com.budgetSystem.budget.Controller;

import java.util.List;
import com.budgetSystem.budget.dto.budget.BudgetResponse;
import com.budgetSystem.budget.dto.budget.CreateBudgetRequest;
import com.budgetSystem.budget.dto.budget.UpdateBudgetRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.budgetSystem.budget.Service.BudgetService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/budgets")
public class BudgetController {
    private BudgetService budgetService;

    @PostMapping("/create")
    public ResponseEntity<BudgetResponse> addBudget(@Valid @RequestBody CreateBudgetRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.addBudget(request));

    }

    @GetMapping("/findAll/{categoryId}")
    public ResponseEntity<List<BudgetResponse>> getBudgetsbyCategoryId(@Valid @PathVariable Integer categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.getBudgetsByCategoryID(categoryId));
    }

    @DeleteMapping("/delete/{budgetId}")
    public ResponseEntity<?> deleteBudget(@Valid @PathVariable Integer budgetId) {
       budgetService.deleteBudget(budgetId);
       return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<BudgetResponse> updateBudget(@Valid @RequestBody UpdateBudgetRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.updateBudget(request));
    }

    @GetMapping("/find/{budgetId}")
    public ResponseEntity<BudgetResponse> getBudget(@Valid @PathVariable Integer budgetId) {
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.getBudgetById(budgetId));
    }


}
