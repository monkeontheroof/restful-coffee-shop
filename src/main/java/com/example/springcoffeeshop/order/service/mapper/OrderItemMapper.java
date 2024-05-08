package com.example.springcoffeeshop.order.service.mapper;

import com.example.springcoffeeshop.order.entity.OrderItemEntity;
import com.example.springcoffeeshop.order.service.model.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    List<OrderItem> toDtos(List<OrderItemEntity> orderItemEntities);
    OrderItem toDto(OrderItemEntity orderItemEntity);

    List<OrderItemEntity> toEntities(List<OrderItem> orderItems);
    OrderItemEntity toEntity(OrderItem orderItem);
}
