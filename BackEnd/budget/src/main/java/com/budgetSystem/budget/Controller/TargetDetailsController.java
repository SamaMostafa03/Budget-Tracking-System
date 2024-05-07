package com.budgetSystem.budget.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.budgetSystem.budget.Service.TargetDetailsService;
import jakarta.validation.constraints.Positive;

@RestController
public class TargetDetailsController {
  @Autowired
  private final TargetDetailsService targetDetailsService;

  public TargetDetailsController(TargetDetailsService targetDetailsService) {
    this.targetDetailsService = targetDetailsService;
  }

  @PostMapping("/calculateAndSaveRemainingAmount")
  public ResponseEntity<?> calculateAndSaveRemainingAmount(@Valid @RequestParam Integer targetId,
      @Valid @RequestParam @Positive double assignedMoney) {
    return ResponseEntity
        .ok(new SuccessResponse(targetDetailsService.calculateAndSaveRemainingAmount(targetId, assignedMoney)));
  }

  @GetMapping("/targetdetails")
  public ResponseEntity<?> getTargetDetails(@Valid @RequestParam Integer targetId) {
    return ResponseEntity.ok(new SuccessResponse(targetDetailsService.getALLTargetDetails(targetId)));
  }
}
