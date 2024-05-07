package com.example.springcoffeeshop.order.service.model;

import com.example.springcoffeeshop.order.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long id;

    private String drinkName;

    private Long drinkId;

    private String drinkImage;

    private Double price;

    private Double total;

    private Long count;
}
