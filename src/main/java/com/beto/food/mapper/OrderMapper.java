package com.beto.food.mapper;

import com.beto.food.dto.OrderDTO;
import com.beto.food.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends BaseMapper<Order, OrderDTO>{
}
