package com.sinem.mapper;

import com.sinem.dto.request.RestaurantSaveRequestDto;
import com.sinem.repository.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRestaurantMapper {
    IRestaurantMapper INSTANCE = Mappers.getMapper(IRestaurantMapper.class);

    Restaurant fromSaveDtoToRestaurant(final RestaurantSaveRequestDto dto);
}
