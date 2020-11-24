package com.beto.food.mapper;

import com.beto.food.dto.AddressDTO;
import com.beto.food.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper extends BaseMapper<Address, AddressDTO> {
}

