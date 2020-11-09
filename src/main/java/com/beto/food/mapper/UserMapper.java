package com.beto.food.mapper;

import com.beto.food.dto.UserDTO;
import com.beto.food.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserDTO> {
}
