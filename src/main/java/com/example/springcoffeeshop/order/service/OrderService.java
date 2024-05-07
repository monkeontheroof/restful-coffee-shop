package com.example.springcoffeeshop.order.service;

import com.example.springcoffeeshop.order.entity.OrderEntity;
import com.example.springcoffeeshop.order.entity.OrderStatus;
import com.example.springcoffeeshop.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No order found"));
    }

    public OrderEntity getOrderByUserIdAndStatus(Long userId, OrderStatus orderStatus) {
        return orderRepository.findByUserEntityIdAndStatus(userId, orderStatus).orElseThrow(() -> new EntityNotFoundException("No order found"));
    }

    public void save(OrderEntity order){
        orderRepository.save(order);
    }
}
