package com.sinem.controller;

import com.sinem.dto.request.ProductSaveRequestDto;
import com.sinem.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody @Valid ProductSaveRequestDto dto){
        productService.save(dto);
        return ResponseEntity.ok(true);
    }
}
