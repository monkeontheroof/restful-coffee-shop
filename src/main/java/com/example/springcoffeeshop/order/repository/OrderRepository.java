package com.example.springcoffeeshop.order.repository;

import com.example.springcoffeeshop.order.entity.OrderEntity;
import com.example.springcoffeeshop.order.entity.OrderStatus;
import com.example.springcoffeeshop.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    Optional<OrderEntity> findByUserEntityIdAndStatus(Long userId, OrderStatus orderStatus);

    List<OrderEntity> findAllByUserEntityOrderByDateDesc(UserEntity userEntity);
}
