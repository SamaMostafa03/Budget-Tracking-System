package com.budgetSystem.transaction.clients;

import com.sama.budget.BudgetResponse;
import com.sama.budget.UpdateBudgetRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Configuration
@FeignClient(name="BUDGET-SERVICE" , url="${application.config.budget-url}")
public interface BudgetClient {

    @PutMapping("/update")
    public ResponseEntity<BudgetResponse> updateBudget(@Valid @RequestBody UpdateBudgetRequest request) ;

    @GetMapping("/find/{budgetId}")
    public ResponseEntity<BudgetResponse> getBudget(@Valid @PathVariable Integer budgetId) ;

}
