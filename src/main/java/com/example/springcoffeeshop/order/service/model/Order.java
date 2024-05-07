package com.example.springcoffeeshop.order.service.model;

import com.example.springcoffeeshop.order.entity.OrderStatus;
import com.example.springcoffeeshop.user.entity.CartItemEntity;
import com.example.springcoffeeshop.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Long id;

    private UUID trackingId;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    private Double amount;

    private Double totalAmount;

    private String address;

    private String payment;

    private Long discount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Long userId;

    private List<CartItemEntity> cartItemEntities;
}
