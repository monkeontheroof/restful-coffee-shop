package com.example.springcoffeeshop.drink.repository;

import com.example.springcoffeeshop.drink.entity.DrinkEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkEntity, Long> {
    boolean existsByName(String name);

    @Query("SELECT d FROM DrinkEntity d JOIN FETCH d.categoryEntity")
    List<DrinkEntity> findAllDrinks();

    DrinkEntity findByName(String trim);
}
