package com.example.springcoffeeshop.category.service.mapper;

import com.example.springcoffeeshop.category.entity.CategoryEntity;
import com.example.springcoffeeshop.category.service.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

//    @Mapping(target = "drinks", source = "drinkEntities")
    Category toDto(CategoryEntity categoryEntity);

//    @Mapping(target = "drinkEntities", source = "drinks")
    CategoryEntity toEntity(Category category);

//    @Mapping(target = "drinks", source = "drinkEntities")
    List<Category> toDtos(List<CategoryEntity> categoryEntities);

//    @Mapping(target = "drinkEntities", source = "drinks")
    List<CategoryEntity> toEntities(List<Category> categories);
}
