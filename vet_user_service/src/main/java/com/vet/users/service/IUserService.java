package com.vet.users.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;

import com.vet.commons.dtos.res.UserDto;
import com.vet.users.dtos.CreateUserDto;
import com.vet.users.dtos.UpdateUserDto;
import com.vet.users.entities.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService extends ReactiveUserDetailsService {
     Mono<UserDto> findOneById(Long id);
     Mono<UserDto> create(CreateUserDto createUserDto);
     Mono<UserDto> update(Long id, UpdateUserDto updateUserDto);
     Flux<UserDto> findAll();
     Mono<Void> delete(Long id);
     Mono<User> getDtoVerified(User dto);
}
