package com.budgetSystem.budget.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.budgetSystem.budget.Model.Category;
import com.budgetSystem.budget.Service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    SuccessResponse successResponse = new SuccessResponse();

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category, @RequestParam Integer budgetId) {
        categoryService.addCategory(budgetId, category);
        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<?> deleteCategory(@RequestParam Integer categoryId, @RequestParam Integer clientId) {
        categoryService.deleteCategory(categoryId, clientId);
        return ResponseEntity.ok(successResponse);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<?> updateCategoryName(
            @Valid @RequestParam Integer clientId,
            @Valid @RequestParam Integer categoryId,
            @Valid @RequestParam String newName) {

        return ResponseEntity.ok(new SuccessResponse(
                categoryService.updateCategoryName(clientId, categoryId, newName)));
    }

    @GetMapping("/getcategory")
    public ResponseEntity<?> getCategoryByClient(@Valid @RequestParam Integer clientId,
            @Valid @RequestParam Integer categoryId) {
        return ResponseEntity.ok(new SuccessResponse(categoryService.getCategoryById(clientId, categoryId)));
    }

    @GetMapping("/getcategoryByClient/{clientId}")
    public ResponseEntity<?> getNumberOfAllCategoriesByClientID(@PathVariable Integer clientId) {
        return ResponseEntity.ok(new SuccessResponse(categoryService.getNumberOfAllCategoriesByClientID(clientId)));
    }

}
