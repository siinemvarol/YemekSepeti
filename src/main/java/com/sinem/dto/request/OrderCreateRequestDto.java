package com.sinem.dto.request;

import com.sinem.repository.entity.Customer;
import com.sinem.repository.entity.Restaurant;

import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateRequestDto {
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Restaurant restaurant;
}
