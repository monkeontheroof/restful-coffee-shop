package com.example.springcoffeeshop.order.entity;

import com.example.springcoffeeshop.category.entity.CategoryEntity;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull(message = "Address cannot be null")
    private String address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderEntity", cascade = CascadeType.PERSIST)
    private List<OrderItemEntity> orderItemEntities;

//    @NotNull(message = "Amount cannot be null")
//    @PositiveOrZero(message = "Amount must be positive or zero")
//    private Double amount;
//
//    @NotNull(message = "Total amount cannot be null")
//    @PositiveOrZero(message = "Total amount must be positive or zero")
//    private Double totalAmount;

//    @NotNull(message = "Discount cannot be null")
//    @PositiveOrZero(message = "Discount must be positive or zero")
//    private Long discount;

//    @NotNull(message = "Payment method cannot be null")
//    private String payment;
}
