package com.example.springcoffeeshop.user.repository;

import com.example.springcoffeeshop.user.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    Optional<CartItemEntity> findByDrinkEntityIdAndOrderEntityIdAndUserEntityId(Long drinkId, Long orderId, Long userId);
}
