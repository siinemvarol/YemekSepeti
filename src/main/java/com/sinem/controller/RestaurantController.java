package com.sinem.controller;

import com.sinem.dto.request.ProductSaveRequestDto;
import com.sinem.dto.request.RestaurantSaveRequestDto;
import com.sinem.dto.response.ProductFindAllResponseDto;
import com.sinem.dto.response.RestaurantFindAllResponseDto;
import com.sinem.repository.entity.Restaurant;
import com.sinem.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody @Valid RestaurantSaveRequestDto dto){
        restaurantService.save(dto);
        return ResponseEntity.ok(true);
    }
    @PostMapping("/save-product-to-restaurant")
    public ResponseEntity<Boolean> saveProductToRestaurant(Long productId, Long restaurantId){
        restaurantService.saveProductToRestaurant(productId, restaurantId);
        return ResponseEntity.ok(true);
    }
    @GetMapping("/find-all-restaurants")
    public ResponseEntity<List<RestaurantFindAllResponseDto>> findAllRestaurants(){
        return ResponseEntity.ok(restaurantService.findAllRestaurants());
    }

    @GetMapping("/find-all-products")
    public ResponseEntity<List<ProductFindAllResponseDto>> findAllProducts(Long restaurantId){
        return ResponseEntity.ok(restaurantService.findAllProducts(restaurantId));
    }
}


