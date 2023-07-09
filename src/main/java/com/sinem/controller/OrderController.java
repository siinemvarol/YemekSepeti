package com.sinem.controller;

import com.sinem.dto.response.OrderFindAllByCustomerResponseDto;
import com.sinem.dto.response.OrderFindAllByRestaurantResponseDto;
import com.sinem.dto.response.OrderFindAllResponseDto;
import com.sinem.repository.entity.Customer;
import com.sinem.repository.entity.Order;
import com.sinem.repository.entity.Restaurant;
import com.sinem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<Boolean> createOrder(Long customerId, Long restaurantId){
        return ResponseEntity.ok(orderService.createOrder(customerId,restaurantId));
    }
    @PostMapping("/add-product-to-order")
    public ResponseEntity<Boolean> addProductToOrder(Long customerId, Long orderId, Long productId, Long restaurantId){
        return ResponseEntity.ok(orderService.addProductToOrder(customerId, orderId, productId, restaurantId));
    }

    @GetMapping("/find-all-orders-by-customer")
    public ResponseEntity<List<OrderFindAllByCustomerResponseDto>> findAllOrdersByCustomer(Long customerId){
        return ResponseEntity.ok(orderService.findAllOrdersByCustomer(customerId));
    }
    @GetMapping("/list-all-orders")
    public ResponseEntity<List<OrderFindAllResponseDto>> listAllOrders(){
        return ResponseEntity.ok(orderService.listAllOrders());
    }
    @GetMapping("/find-all-orders-by-restaurant")
    public ResponseEntity<List<OrderFindAllByRestaurantResponseDto>> findAllOrdersByRestaurant(Long restaurantId){
        return ResponseEntity.ok(orderService.findAllOrdersByRestaurant(restaurantId));
    }
}
