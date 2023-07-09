package com.sinem.mapper;

import com.sinem.dto.request.CustomerLoginRequestDto;
import com.sinem.dto.request.CustomerRegisterRequestDto;
import com.sinem.repository.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICustomerMapper {

    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

    Customer fromRegisterDtoToCustomer(final CustomerRegisterRequestDto dto);
    Customer fromLoginDtoToCustomer(final CustomerLoginRequestDto dto);
}
