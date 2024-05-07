package com.example.springcoffeeshop.user.service.mapper;

import com.example.springcoffeeshop.user.entity.CartItemEntity;
import com.example.springcoffeeshop.user.service.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(target = "drinkName",  source = "drinkEntity.name")
    @Mapping(target = "drinkImage",  source = "drinkEntity.imagePath")
    @Mapping(target = "drinkId",  source = "drinkEntity.id")
    @Mapping(target = "orderId",  source = "orderEntity.id")
    @Mapping(target = "userId",  source = "userEntity.id")
    CartItem toDto(CartItemEntity cartItemEntity);

    List<CartItem> toDtos(List<CartItemEntity> cartItemEntities);


    CartItemEntity toEntity(CartItem cartItem);
    List<CartItemEntity> toEntities(List<CartItem> cartItems);
}
