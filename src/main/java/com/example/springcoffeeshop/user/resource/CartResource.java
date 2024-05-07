package com.example.springcoffeeshop.user.resource;

import com.example.springcoffeeshop.user.service.CartService;
import com.example.springcoffeeshop.user.service.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/cart")
public class CartResource {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartItem> addDrinkToCart(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok(cartService.addDrinkToCart(request));
    }
}
