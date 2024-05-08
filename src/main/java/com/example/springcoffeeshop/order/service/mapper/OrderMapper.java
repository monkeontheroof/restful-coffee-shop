package com.example.springcoffeeshop.order.service.mapper;

import com.example.springcoffeeshop.order.entity.OrderEntity;
import com.example.springcoffeeshop.order.service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    List<Order> toDtos(List<OrderEntity> orderEntities);
    Order toDto(OrderEntity orderEntity);

    List<OrderEntity> toEntities(List<Order> orders);

    OrderEntity toEntity(Order order);
}
