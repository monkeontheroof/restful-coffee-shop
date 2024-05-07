package com.example.springcoffeeshop.order.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
public class AddOrderItemRequest {

    @NotNull(message = "Drink cannot be null")
    private Long drinkId;

    @NotNull(message = "Quantity cannot be null")
    private Long count;
}
