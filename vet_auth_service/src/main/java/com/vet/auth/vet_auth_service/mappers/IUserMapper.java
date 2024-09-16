package com.vet.auth.vet_auth_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.vet.auth.vet_auth_service.entities.User;
import com.vet.commons.dtos.res.UserDto;

@Mapper(componentModel = "spring")
public interface IUserMapper {


    default User dtoToEntity(UserDto dto) {
        User entity = new User();
        setDtoOnEntity(dto, entity);
        return entity;
    } 

    default UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        setEntityOnDto(entity, dto);
        return dto;
    }



    void setEntityOnDto(User entity, @MappingTarget UserDto dto);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    void setDtoOnEntity(UserDto dto, @MappingTarget User entity);
  
}
