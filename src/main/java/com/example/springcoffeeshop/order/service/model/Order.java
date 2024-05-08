package com.example.springcoffeeshop.order.service.model;

import com.example.springcoffeeshop.order.entity.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    private String address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Long userId;

//    private Double amount;
//
//    private Double totalAmount;
//
//    private String payment;
//
//    private Long discount;
}
