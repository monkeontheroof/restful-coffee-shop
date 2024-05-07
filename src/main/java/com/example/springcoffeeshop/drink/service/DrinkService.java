package com.example.springcoffeeshop.drink.service;

import com.example.springcoffeeshop.category.entity.CategoryEntity;
import com.example.springcoffeeshop.category.service.CategoryService;
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

import static java.util.Objects.isNull;

@Service
public class DrinkService {
    @Autowired
    private DrinkMapper drinkMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Drink> getAllDrinks() {
        return drinkMapper.toDtos(drinkRepository.findAllDrinks());
    }

    public Drink getDrinkById(Long id) {
        return drinkMapper.toDto(drinkRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Drink not found")));
    }

    public Category getCategoryById(Long id) {
        Drink drink = getDrinkById(id);
        Category category = categoryService.getCategoryById(drink.getCategoryId());
        return category;
    }

    public Drink addDrink(Drink drink) {
        verifyDrink(drink);

        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryService.getCategoryById(drink.getCategoryId()));
        drinkMapper.toEntity(drink).setCategoryEntity(categoryEntity);
        DrinkEntity createdDrink = drinkRepository.save(drinkMapper.toEntity(drink));
        return drinkMapper.toDto(createdDrink);
    }

    public Drink updateDrinkById(Long id, Drink drink) {
        if (drink.getCategoryId().intValue() < 0 || isNull(categoryService.getCategoryById(drink.getCategoryId())))
            throw new InputValidationException("Category not found");

        DrinkEntity updatedDrink = drinkMapper.toEntity(getDrinkById(id));

        if(!drink.getName().equals(updatedDrink.getName()) && drinkRepository.existsByName(drink.getName()))
            throw new InputValidationException("Drink name already exists");
        updateDrink(drink, updatedDrink);
        return drinkMapper.toDto(drinkRepository.save(updatedDrink));
    }

    public void deleteDrinkById(Long id) {
        Drink drink = getDrinkById(id);
        drinkRepository.deleteById(drink.getId());
    }

    private void verifyDrink(Drink drink) {
//        if (drink.getName().trim().isBlank() || drink.getName().trim().isEmpty())
//            throw new InputValidationException("Drink name is required");
        if(drinkRepository.existsByName(drink.getName().trim()))
            throw new InputValidationException("Drink name already exists");

        if (drink.getCategoryId().intValue() < 0 || isNull(categoryService.getCategoryById(drink.getCategoryId())))
            throw new InputValidationException("Category not found");

//        if(drink.getPrice().isInfinite() || drink.getPrice().isNaN() || drink.getPrice().doubleValue() < 0.0)
//            throw new InputValidationException("Invalid price");
    }
    private void checkDuplicatedDrinkName(Drink drink) {
        DrinkEntity drinkEntity = drinkRepository.findByName(drink.getName().trim());
        if (!drink.getId().equals(drinkEntity.getId()) && drinkEntity.getName().equals(drink.getName()))
            throw new InputValidationException("Drink name already exists");
    }

    private void updateDrink(Drink drink, DrinkEntity drinkEntity) {
        drinkEntity.setName(drink.getName());
        drinkEntity.setQuantity(drink.getQuantity());
        drinkEntity.setDescription(drink.getDescription());
        drinkEntity.setPrice(drink.getPrice());
        drinkEntity.setCategoryEntity(categoryMapper.toEntity(categoryService.getCategoryById(drink.getCategoryId())));
    }
}
