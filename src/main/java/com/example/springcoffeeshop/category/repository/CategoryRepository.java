package com.example.springcoffeeshop.category.repository;

import com.example.springcoffeeshop.category.entity.CategoryEntity;
import com.example.springcoffeeshop.category.service.model.Category;
import com.example.springcoffeeshop.drink.entity.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);

    List<CategoryEntity> findAllByName(String name);
}
