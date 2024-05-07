package com.example.springcoffeeshop.order.resource;

import com.example.springcoffeeshop.order.entity.AddOrderItemRequest;
import com.example.springcoffeeshop.order.entity.OrderItemEntity;
import com.example.springcoffeeshop.order.service.OrderItemService;
import com.example.springcoffeeshop.order.service.OrderService;
import com.example.springcoffeeshop.order.service.model.Order;
import com.example.springcoffeeshop.order.service.model.OrderItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Order")
@RequestMapping("/v1/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    @Operation(
            description = "Get all orders",
            summary = "Get all orders",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get all orders successfully")
            }
    )
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

//    @PostMapping
//    public ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) {
//        return ResponseEntity.ok(orderService.save(order));
//    }

    @PostMapping("/items")
    @Operation(
            description = "Add order items",
            summary = "Add order items",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Add order items successfully")
            }
    )
    public ResponseEntity<List<OrderItem>> addItems(@Valid @RequestBody List<AddOrderItemRequest> request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(orderItemService.addOrderItem(request, authentication));
    }
}
