package com.example.springcoffeeshop.drink.service.mapper;

import com.example.springcoffeeshop.drink.entity.DrinkEntity;
import com.example.springcoffeeshop.drink.service.model.Drink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DrinkMapper {
    @Mapping(target = "categoryId", source = "categoryEntity.id")
    Drink toDto(DrinkEntity drinkEntity);

    List<Drink> toDtos(List<DrinkEntity> drinkEntities);

    @Mapping(target = "categoryEntity.id", source = "categoryId")
    DrinkEntity toEntity(Drink drink);

    List<DrinkEntity> toEntities(List<Drink> drinks);
}
