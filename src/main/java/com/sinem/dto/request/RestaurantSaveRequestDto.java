package com.sinem.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantSaveRequestDto {
    @NotEmpty
    private String name;
    private Double point;
    @NotEmpty
    private String address;
}
