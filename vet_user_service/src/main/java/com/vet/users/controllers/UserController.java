package com.vet.users.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.auth_common.dtos.CreateUserDto;
import com.vet.auth_common.dtos.UpdateUserDto;
import com.vet.auth_common.entities.User;
import com.vet.commons.dtos.req.FindUsersQueryDto;
import com.vet.commons.dtos.res.FindAllResultDto;
import com.vet.commons.dtos.res.UserDto;

import com.vet.users.service.IUserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public Mono<UserDto> create(@RequestBody CreateUserDto createUserDto) {
        return userService.create(createUserDto);
    }


    @GetMapping
    public Mono<FindAllResultDto<UserDto>> findAll(@ModelAttribute FindUsersQueryDto queryDto) {
        return userService.findAll(queryDto);
    }

    @GetMapping("/username/{username}")
    public Mono<User> findOneByUsername(@PathVariable String username) {
        return userService.findByUsername(username).cast(User.class);
    }

    @GetMapping("/{id}")
    public Mono<UserDto> findOneId(@PathVariable Long id) {
        return userService.findOneById(id);
    }

    

    @PutMapping("/{id}")
    public Mono<UserDto> update(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        return userService.update(id, updateUserDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    
}
