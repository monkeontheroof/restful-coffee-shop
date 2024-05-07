package com.example.springcoffeeshop.user.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddDrinkToCartRequest {

    private Long userId;
    private Long drinkId;
}
