package com.example.springcoffeeshop.order.service;

import com.example.springcoffeeshop.order.entity.OrderEntity;
import com.example.springcoffeeshop.order.entity.OrderStatus;
import com.example.springcoffeeshop.order.repository.OrderItemRepository;
import com.example.springcoffeeshop.order.repository.OrderRepository;
import com.example.springcoffeeshop.order.service.mapper.OrderItemMapper;
import com.example.springcoffeeshop.order.service.mapper.OrderMapper;
import com.example.springcoffeeshop.order.service.model.Order;
import com.example.springcoffeeshop.order.service.model.OrderItem;
import com.example.springcoffeeshop.user.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public List<Order> getAllOrders() {
        return orderMapper.toDtos(orderRepository.findAll());
    }

    public Order getOrderById(UUID id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No order found"));
        return orderMapper.toDto(orderEntity);
    }

    public OrderEntity getOrderByUserIdAndStatus(Long userId, OrderStatus orderStatus) {
        return orderRepository.findByUserEntityIdAndStatus(userId, orderStatus).orElseThrow(() -> new EntityNotFoundException("No order found"));
    }

    public Order save(Order order){
        return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(order)));
    }

    public List<Order> getOrdersHistory(Authentication authentication) {
        UserEntity userEntity = (UserEntity) userDetailsService.loadUserByUsername(authentication.getName());
        return orderMapper.toDtos(userEntity.getOrderEntities());
    }

    public List<OrderItem> getOrderDetailsById(UUID id) {
        return orderItemMapper.toDtos(orderItemRepository.findAllByOrderEntityId(id));
    }
}
