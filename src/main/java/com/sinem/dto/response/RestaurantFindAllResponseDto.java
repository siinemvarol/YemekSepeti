package com.sinem.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantFindAllResponseDto {
    private Long id;
    @NotEmpty
    private String name;
    private Double point;
    @NotEmpty
    private String address;
}
