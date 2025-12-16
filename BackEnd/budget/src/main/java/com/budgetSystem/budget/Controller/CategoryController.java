package com.budgetSystem.budget.Controller;

import com.budgetSystem.budget.dto.budget.UpdateBudgetRequest;
import com.budgetSystem.budget.dto.category.CategoryResponse;
import com.budgetSystem.budget.dto.category.CreateCategoryReq;
import com.budgetSystem.budget.dto.category.UpdateCategoryReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CreateCategoryReq req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.addCategory(req));
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@Valid @PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/update")
    public ResponseEntity<CategoryResponse> updateCategory(@Valid @RequestBody UpdateCategoryReq request) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategoryName(request));
    }

    @GetMapping("/find/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategory(@Valid @PathVariable Integer categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(categoryId));
    }

    @GetMapping("/findAll/{clientId}")
    public ResponseEntity<List<CategoryResponse>> getNumberOfAllCategoriesByClientID(@Valid @PathVariable Integer clientId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategoriesByClient(clientId));
    }

}
