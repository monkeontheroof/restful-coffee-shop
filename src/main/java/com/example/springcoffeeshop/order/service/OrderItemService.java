package com.example.springcoffeeshop.order.service;

import com.example.springcoffeeshop.drink.entity.DrinkEntity;
import com.example.springcoffeeshop.drink.service.DrinkService;
import com.example.springcoffeeshop.drink.service.mapper.DrinkMapper;
import com.example.springcoffeeshop.drink.service.model.Drink;
import com.example.springcoffeeshop.order.entity.AddOrderItemRequest;
import com.example.springcoffeeshop.order.entity.OrderEntity;
import com.example.springcoffeeshop.order.entity.OrderItemEntity;
import com.example.springcoffeeshop.order.entity.OrderStatus;
import com.example.springcoffeeshop.order.repository.OrderItemRepository;
import com.example.springcoffeeshop.order.repository.OrderRepository;
import com.example.springcoffeeshop.order.service.mapper.OrderItemMapper;
import com.example.springcoffeeshop.order.service.model.OrderItem;
import com.example.springcoffeeshop.user.entity.UserEntity;
import com.example.springcoffeeshop.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private DrinkMapper drinkMapper;

    public List<OrderItemEntity> getOrderItems(){
        return orderItemRepository.findAll();
    }

    public OrderItemEntity getOrderItemById(Long id){
        return orderItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order item not found"));
    }

    public List<OrderItem> addOrderItem(List<AddOrderItemRequest> orderItemRequest, Authentication authentication){
        List<DrinkEntity> drinks = new ArrayList<>();
        UserEntity userEntity = (UserEntity) userDetailsService.loadUserByUsername(authentication.getName());

        OrderEntity orderEntity = OrderEntity.builder()
                .userEntity(userEntity)
                .date(LocalDate.now())
                .address("your address")
                .status(OrderStatus.PLACED)
                .orderItemEntities(new ArrayList<>())
                .build();

        orderRepository.save(orderEntity);

        for(AddOrderItemRequest item : orderItemRequest){
            DrinkEntity drink = drinkMapper.toEntity(drinkService.getDrinkById(item.getDrinkId()));
            double totalPrice = drink.getPrice() * item.getCount();
            OrderItemEntity orderItem = OrderItemEntity.builder()
                    .count(item.getCount())
                    .orderEntity(orderEntity)
                    .price(drink.getPrice())
                    .drinkImage(drink.getImagePath())
                    .drinkId(drink.getId())
                    .drinkName(drink.getName())
                    .total(totalPrice)
                    .build();
            orderItemRepository.save(orderItem);
            orderEntity.getOrderItemEntities().add(orderItem);
        }
        orderRepository.save(orderEntity);
        return orderItemMapper.toDtos(orderEntity.getOrderItemEntities());
    }
}
