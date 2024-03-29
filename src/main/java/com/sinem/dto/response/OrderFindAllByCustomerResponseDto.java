package com.sinem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFindAllByCustomerResponseDto {
    private Long orderId;
    private String customerName;
    private Long restaurantId;
    private String restaurantName;

}
