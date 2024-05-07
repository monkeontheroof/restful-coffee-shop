package com.example.springcoffeeshop.drink.resource;

import com.example.springcoffeeshop.category.service.model.Category;
import com.example.springcoffeeshop.drink.service.DrinkService;
import com.example.springcoffeeshop.drink.service.model.Drink;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "Drink")
@RequestMapping("/v1/drinks")
public class DrinkResource {
    @Autowired
    private DrinkService drinkService;

    @GetMapping
    @Operation(
            description = "Get all drinks",
            summary = "Get all drinks",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get all drinks successfully"),
                    @ApiResponse(responseCode = "404", description = "Drink not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized / Invalid Token")
            }
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<Drink>> getAllDrinks() {
        return ResponseEntity.ok(drinkService.getAllDrinks());
    }

    @GetMapping("/{id:\\d+}")
    @Operation(
            description = "Get drink by id",
            summary = "Get drink by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get drink by id successfully"),
                    @ApiResponse(responseCode = "404", description = "Drink not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized / Invalid Token")
            }
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Drink> getDrinkById(@PathVariable("id") Long id){
        return ResponseEntity.ok(drinkService.getDrinkById(id));
    }

    @GetMapping("/{id:\\d+}/category")
    @Operation(
            description = "Get category by id",
            summary = "Get category by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get category by id successfully"),
                    @ApiResponse(responseCode = "404", description = "Category not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized / Invalid Token")
            }
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(drinkService.getCategoryById(id));
    }

    @PostMapping
    @Operation(
            description = "Add a new drink",
            summary = "Add a new drink",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Added new drink successfully"),
                    @ApiResponse(responseCode = "400", description = "Validation failed"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized / Invalid Token")
            }
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Drink> addDrink(@Valid @RequestBody Drink drink) {
        Drink createdDrink = drinkService.addDrink(drink);
        return ResponseEntity.created(URI.create("drinks/" + createdDrink.getId())).body(createdDrink);
    }

    @PutMapping("/{id:\\d+}")
    @Operation(
            description = "Update a drink",
            summary = "Update a drink",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updated drink successfully"),
                    @ApiResponse(responseCode = "400", description = "Validation failed for drink"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized / Invalid Token")
            }
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Drink> updateDrinkById(@PathVariable("id") Long id, @Valid @RequestBody Drink drink) {
        return ResponseEntity.ok(drinkService.updateDrinkById(id, drink));
    }

    @DeleteMapping("/{id:\\d+}")
    @Operation(
            description = "Delete a drink",
            summary = "Delete a drink",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Deleted drink successfully"),
                    @ApiResponse(responseCode = "404", description = "Drink not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized / Invalid Token")
            }
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> deleteDrinkById(@PathVariable("id") Long id) {
        drinkService.deleteDrinkById(id);
        return ResponseEntity.noContent().build();
    }
}
