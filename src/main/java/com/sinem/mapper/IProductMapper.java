package com.sinem.mapper;

import com.sinem.dto.request.ProductSaveRequestDto;
import com.sinem.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {

    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    Product fromSaveDtoToProduct(final ProductSaveRequestDto dto);
}
