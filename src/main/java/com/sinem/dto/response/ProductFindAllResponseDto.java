package com.sinem.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductFindAllResponseDto {
    private Long id;
    @NotEmpty
    private String name;
    private String category;
    @NotNull
    private Double cost;
}
