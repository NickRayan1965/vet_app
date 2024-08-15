package com.vet.users.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.vet.users.dtos.CreateUserDto;
import com.vet.users.dtos.UpdateUserDto;
import com.vet.users.dtos.UserDto;
import com.vet.users.entities.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    default User dtoToEntity(CreateUserDto dto) {
        User entityUser = new User();
        mergeToEntity(dto, entityUser);
        return entityUser;
    }

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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    void mergeToEntity(CreateUserDto dto, @MappingTarget User entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    void mergeToEntity(UpdateUserDto dto, @MappingTarget User entity);

    void setEntityOnDto(User entity, @MappingTarget UserDto dto);

    @Mapping(target = "password", ignore = true)
    void setDtoOnEntity(UserDto dto, @MappingTarget User entity);
  
}
