package com.example.springcoffeeshop.order.entity;

import com.example.springcoffeeshop.category.entity.CategoryEntity;
import com.example.springcoffeeshop.drink.entity.DrinkEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_item")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String drinkName;

    private Long drinkId;

    private String drinkImage;

    private Double price;

    private Double total;

    private Long count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity orderEntity;

//    @ManyToMany
//    @JoinTable(
//            name = "bill_order",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "drink_id")
//    )
//    private List<DrinkEntity> drinkEntities;
}
