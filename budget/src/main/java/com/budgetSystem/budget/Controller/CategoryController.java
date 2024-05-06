package com.budgetSystem.budget.Controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.budgetSystem.budget.Model.Category;
import com.budgetSystem.budget.Service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    SuccessResponse successResponse = new SuccessResponse();

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@Valid @RequestBody Category category)
    {
        categoryService.addCategory(category);
        return ResponseEntity.ok(successResponse);
    }
    
    @DeleteMapping("/deleteCategory")
    public ResponseEntity<?> deleteCategory(@RequestParam Integer categoryId , @RequestParam Integer clientId)
    {
        categoryService.deleteCategory(categoryId,clientId);
         return ResponseEntity.ok(successResponse);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<?> updateCategoryName(
            @Valid @RequestParam Integer clientId,
            @Valid @RequestParam Integer categoryId,
            @Valid @RequestParam String newName)
    {
        categoryService.updateCategoryName(clientId, categoryId, newName);
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/getcategory")
    public ResponseEntity<?> getCategoryByClient(@Valid @RequestParam Integer clientId,
                                        @Valid @RequestParam Integer categoryId)
    {
        return ResponseEntity.ok(new SuccessResponse(categoryService.getCategoryById(clientId,categoryId)));
    }

    @GetMapping("/getcategoryByClient/{clientId}")
    public ResponseEntity<?> getNumberOfAllCategoriesByClientID(@PathVariable Integer clientId) {
        return ResponseEntity.ok(new SuccessResponse(categoryService.getNumberOfAllCategoriesByClientID(clientId)));
    }

}
