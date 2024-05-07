package com.example.springcoffeeshop.drink.service.model;

import com.example.springcoffeeshop.category.entity.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Drink {
    private Long id;

    @NotBlank(message = "Name is required")
    @NotNull(message = "Name cannot be null")
    private String name;

    @DecimalMax(value = "9999.99", message = "Price must be less than $99.99")
    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be between 0 and 9999.99")
    private Double price;

    @Min(value = 0, message = "Quantity must be positive")
    @NotNull(message = "Quantity is cannot be null")
    private Integer quantity;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Image path cannot be null")
    private String imagePath;

    @NotNull(message = "Category cannot be null")
    private Long categoryId;
}
