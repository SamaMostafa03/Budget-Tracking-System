package com.budgetSystem.budget.Controller;

import java.time.LocalDate;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.budgetSystem.budget.Service.TargetService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/targets")
public class TargetController {
    @Autowired
    private TargetService targetService;

    @PostMapping("/addTarget")
    public ResponseEntity<?> addTarget(
            @Valid @RequestParam String targetName,
            @Valid @RequestParam LocalDate endDate,
            @RequestParam @Valid double totalMoneyNeeded,
            @Valid @RequestParam Integer categoryId,
            @Valid @RequestParam Integer clientId) {
        targetService.addTarget(targetName, endDate, totalMoneyNeeded, categoryId, clientId);
        return ResponseEntity.ok(new SuccessResponse());

    }

    @GetMapping("/getTargetByClient/{clientId}")
    public ResponseEntity<?> getNumberOfAllTargetsByClientID(@PathVariable Integer clientId) {
        return ResponseEntity.ok(new SuccessResponse(targetService.getNumberOfAllTargetsByClientID(clientId)));
    }

    @GetMapping("/getTargetsForSpecificUser/{clientId}")
    public ResponseEntity<?> getTargetsbyClientID(@PathVariable Integer clientId) {
        return ResponseEntity.ok(new SuccessResponse(targetService.getTargetByClientID(clientId)));
    }

    @DeleteMapping("/deleteTarget")
    public ResponseEntity<?> deleteTarget(@RequestParam Integer targetId, @RequestParam Integer clientId) {
        targetService.deleteTarget(targetId, clientId);

        return ResponseEntity.ok(new SuccessResponse());

    }

}
