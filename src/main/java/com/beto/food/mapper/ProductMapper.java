package com.beto.food.mapper;

import com.beto.food.dto.ProductDTO;
import com.beto.food.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper extends BaseMapper<Product, ProductDTO> {
}
