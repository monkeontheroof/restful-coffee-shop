package com.example.springcoffeeshop.category.service.model;

import com.example.springcoffeeshop.drink.entity.DrinkEntity;
import com.example.springcoffeeshop.drink.service.model.Drink;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @NotBlank(message = "Name is required")
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
