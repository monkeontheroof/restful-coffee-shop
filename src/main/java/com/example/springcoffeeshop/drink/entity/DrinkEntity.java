package com.example.springcoffeeshop.drink.entity;

import com.example.springcoffeeshop.category.entity.CategoryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "drink")
public class DrinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    private String name;

    @DecimalMax(value = "9999.99", message = "Price must be less than $99.99")
    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be between 0 and 9999.99")
    private Double price;

    @Min(value = 0, message = "Quantity must be positive")
    @NotNull(message = "Quantity is required")
    private Integer quantity;

    private String description;

    private String imagePath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity categoryEntity;
}
