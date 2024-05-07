package com.example.springcoffeeshop.user.service.mapper;

import com.example.springcoffeeshop.user.entity.UserEntity;
import com.example.springcoffeeshop.user.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toDto(UserEntity userEntity);
    List<User> toDtos(List<UserEntity> userEntities);
    UserEntity toEntity(User user);
    List<UserEntity> toEntities(List<User> users);
}
