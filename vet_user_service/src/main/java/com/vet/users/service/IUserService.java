package com.vet.users.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;

import com.vet.auth_common.dtos.CreateUserDto;
import com.vet.auth_common.dtos.UpdateUserDto;
import com.vet.auth_common.entities.User;
import com.vet.commons.dtos.req.FindUsersQueryDto;
import com.vet.commons.dtos.res.FindAllResultDto;
import com.vet.commons.dtos.res.UserDto;

import reactor.core.publisher.Mono;

public interface IUserService extends ReactiveUserDetailsService {
     Mono<UserDto> findOneById(Long id);
     Mono<UserDto> create(CreateUserDto createUserDto);
     Mono<UserDto> update(Long id, UpdateUserDto updateUserDto);
     Mono<FindAllResultDto<UserDto>> findAll(FindUsersQueryDto queryDto);
     Mono<Void> delete(Long id);
     Mono<User> getDtoVerified(User dto);
}
