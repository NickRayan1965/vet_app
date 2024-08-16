package com.vet.users.service;

import com.vet.users.dtos.CreateUserDto;
import com.vet.users.dtos.LoginDto;
import com.vet.users.dtos.LoginResponseDto;
import com.vet.users.dtos.UpdateUserDto;
import com.vet.users.dtos.UserDto;
import com.vet.users.entities.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
     Mono<UserDto> findOneById(Long id);
     Mono<LoginResponseDto> login(LoginDto loginDto);
     Mono<User> findOneByUsername(String username);
     Mono<UserDto> create(CreateUserDto createUserDto);
     Mono<UserDto> update(Long id, UpdateUserDto updateUserDto);
     Flux<UserDto> findAll();
     Mono<Void> delete(Long id);
     Mono<User> getDtoVerified(User dto);
}
