package com.example.springcoffeeshop.user.service;

import com.example.springcoffeeshop.drink.entity.DrinkEntity;
import com.example.springcoffeeshop.drink.repository.DrinkRepository;
import com.example.springcoffeeshop.order.service.OrderService;
import com.example.springcoffeeshop.user.entity.CartItemEntity;
import com.example.springcoffeeshop.user.repository.CartItemRepository;
import com.example.springcoffeeshop.order.entity.OrderEntity;
import com.example.springcoffeeshop.order.entity.OrderStatus;
import com.example.springcoffeeshop.order.repository.OrderRepository;
import com.example.springcoffeeshop.user.entity.UserEntity;
import com.example.springcoffeeshop.user.repository.UserRepository;
import com.example.springcoffeeshop.user.service.mapper.CartItemMapper;
import com.example.springcoffeeshop.user.service.model.AddDrinkToCartRequest;
import com.example.springcoffeeshop.user.service.model.CartItem;
import com.example.springcoffeeshop.user.service.model.User;
import com.example.springcoffeeshop.util.exception.InputValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.nio.file.attribute.UserPrincipal;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private UserService userService;

    public CartItem addDrinkToCart(Map<String, String> request) {
        if(request.isEmpty() || !request.containsKey("drinkId") || request.keySet().size() > 1)
            throw new InputValidationException("Drink not found");

        Optional<String> username = userService.getCurrentUsername();

        if(username.isEmpty() || !username.isPresent())
            throw new UsernameNotFoundException("User not found");

        User user = userService.getUserByEmail(username.get());

        Long drinkId = Long.parseLong(request.get("drinkId"));

        OrderEntity activeOrder = orderService.getOrderByUserIdAndStatus(user.getId(), OrderStatus.PENDING);
        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByDrinkEntityIdAndOrderEntityIdAndUserEntityId(
                drinkId,
                activeOrder.getId(),
                user.getId());

        if(optionalCartItemEntity.isPresent()){
            CartItemEntity cartItemEntity = optionalCartItemEntity.get();
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + 1);
            return cartItemMapper.toDto(cartItemRepository.save(cartItemEntity));
        }

        Optional<DrinkEntity> optionalDrinkEntity = drinkRepository.findById(user.getId());
        Optional<UserEntity> optionalUserEntity = userRepository.findById(user.getId());

        if(optionalDrinkEntity.isEmpty() && optionalUserEntity.isEmpty()){
            throw  new InputValidationException("User or drink not found");
        }
        CartItemEntity cart = CartItemEntity.builder()
                .drinkEntity(optionalDrinkEntity.get())
                .price(optionalDrinkEntity.get().getPrice())
                .quantity(1L)
                .userEntity(optionalUserEntity.get())
                .orderEntity(activeOrder)
                .build();
        CartItemEntity updatedCart = cartItemRepository.save(cart);

        activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
        activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
        activeOrder.getCartItemEntities().add(cart);

        orderService.save(activeOrder);

        return cartItemMapper.toDto(updatedCart);
    }


}
