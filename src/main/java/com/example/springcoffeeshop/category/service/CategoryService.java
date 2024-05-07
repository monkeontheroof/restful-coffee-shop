package com.example.springcoffeeshop.category.service;

import com.example.springcoffeeshop.category.entity.CategoryEntity;
import com.example.springcoffeeshop.category.repository.CategoryRepository;
import com.example.springcoffeeshop.category.service.mapper.CategoryMapper;
import com.example.springcoffeeshop.category.service.model.Category;
import com.example.springcoffeeshop.util.exception.InputValidationException;
import com.example.springcoffeeshop.drink.entity.DrinkEntity;
import com.example.springcoffeeshop.drink.repository.DrinkRepository;
import com.example.springcoffeeshop.drink.service.mapper.DrinkMapper;
import com.example.springcoffeeshop.drink.service.model.Drink;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DrinkMapper drinkMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    public List<Category> getAllCategories() {
        return categoryMapper.toDtos(categoryRepository.findAll());
    }

    public List<Drink> getDrinksById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return drinkMapper.toDtos(categoryEntity.getDrinkEntities());
    }

    public Category getCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return categoryMapper.toDto(categoryEntity);
    }

    public Category addCategory(Category category) {
        verifyCategory(category);
        CategoryEntity categoryEntity = categoryRepository.save(categoryMapper.toEntity(category));

        return categoryMapper.toDto(categoryEntity);
    }

    public Category updateCategoryById(Long id, Category category) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(getCategoryById(id));
        if(!categoryEntity.getName().equals(category.getName()) && categoryRepository.existsByName(category.getName()))
            throw new InputValidationException("Category name already exists");
        updateCategory(categoryEntity, category, categoryEntity.getDrinkEntities());

        return categoryMapper.toDto(categoryRepository.save(categoryEntity));
    }

    public void deleteCategoryById(Long id) {
        List<DrinkEntity> drinkEntities = categoryRepository.findById(id).orElseThrow(() -> new InputValidationException("Category not found")).getDrinkEntities();
        drinkEntities.forEach(d -> d.setCategoryEntity(null));
        drinkRepository.saveAll(drinkEntities);
        categoryRepository.deleteById(id);
    }

    private void verifyCategory(Category category){
        if(categoryRepository.existsByName(category.getName().trim()))
            throw new InputValidationException("Category name already exists");

//        if(category.getName().trim().isEmpty() || category.getName().isBlank())
//            throw new InputValidationException("Category name cannot be empty");
    }

    private void updateCategory(CategoryEntity updatedCategory, Category category, List<DrinkEntity> drinks){
        updatedCategory.setName(category.getName());
        updatedCategory.setDescription(category.getName());
        updatedCategory.setDrinkEntities(drinks);
    }
}
