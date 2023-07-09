package com.sinem.mapper;

import com.sinem.dto.request.OrderCreateRequestDto;
import com.sinem.repository.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOrderMapper {
    IOrderMapper INSTANCE = Mappers.getMapper(IOrderMapper.class);

    Order fromCreateDtoToOrder(final OrderCreateRequestDto dto);
}
