package com.example.springcoffeeshop.category.resource;

import com.example.springcoffeeshop.category.service.CategoryService;
import com.example.springcoffeeshop.category.service.mapper.CategoryMapper;
import com.example.springcoffeeshop.category.service.model.Category;
import com.example.springcoffeeshop.drink.entity.DrinkEntity;
import com.example.springcoffeeshop.drink.service.model.Drink;
import com.example.springcoffeeshop.user.entity.RoleEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "Category")
@RequestMapping("/v1/categories")
public class CategoryResource {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping
    @Operation(
            description = "Get all categories",
            summary = "Get all categories",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get all categories successfully")
            }
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id:\\d+}")
    @Operation(
            description = "Get all categories",
            summary = "Get all categories",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get all categories successfully")
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/{id:\\d+}/drinks")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<Drink>> getDrinksById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.getDrinksById(id));
    }

    @PostMapping()
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
        Category createdCategory = categoryService.addCategory(category);
        return ResponseEntity.created(URI.create("categories/" + categoryMapper.toEntity(createdCategory).getId())).body(createdCategory);
    }

    @PutMapping("/{id:\\d+}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Category> updateCategoryById(@Valid @RequestBody Category category,
                                                   @PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.updateCategoryById(id, category));
    }

    @DeleteMapping("/{id:\\d+}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
