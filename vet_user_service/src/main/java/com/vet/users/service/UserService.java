package com.vet.users.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vet.commons.dtos.res.UserDto;
import com.vet.commons.exceptions.NotFoundException;
import com.vet.users.dtos.CreateUserDto;
import com.vet.users.dtos.LoginDto;
import com.vet.users.dtos.LoginResponseDto;
import com.vet.users.dtos.UpdateUserDto;
import com.vet.users.entities.User;
import com.vet.users.entities.mappers.IUserMapper;
import com.vet.users.repositories.IUserRepository;
import com.vet.users.security.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public Mono<UserDto> create(CreateUserDto createUserDto) {
        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
       return Mono
                .just(createUserDto)
                .map(userMapper::dtoToEntity)
                .flatMap(userRepository::save)
                .map(userMapper::toDto);
    }

    @Override
    public Mono<UserDto> update(Long id, UpdateUserDto updateUserDto) {
        if(updateUserDto.getPassword() != null){

        }
        return findOneById(id)
            .map(userMapper::dtoToEntity)
            .flatMap(user -> {
                return getDtoVerified(userMapper.dtoToEntity(updateUserDto))
                .map(u -> user);
            })
            .doOnNext(user -> {
                userMapper.mergeToEntity(updateUserDto, user);
                if (updateUserDto.getPassword() != null) {
                    user.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
                }
            })
            .flatMap(userRepository::save)
            .map(userMapper::toDto);
    }

    @Override
    public Flux<UserDto> findAll() {
        return userRepository
            .findAllEnabled()
            .map(userMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return findOneById(id)
            .map(user -> user.getId())
            .flatMap(userRepository::deleteById)
            ;
    }

    @Override
    public Mono<UserDto> findOneById(Long id) {
        return userRepository
            .findById(id)
            .map(userMapper::toDto)
            .switchIfEmpty(Mono.error(new NotFoundException("User not found")))
            ;
    }

    @Override
    public Mono<User> getDtoVerified(User dto) {
        return Mono.just(dto);
    }

    @Override
    public Mono<User> findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public Mono<LoginResponseDto> login(LoginDto loginDto) {
        return userRepository
            .findOneByUsername(loginDto.getUsername())
            .switchIfEmpty(Mono.error(new BadCredentialsException("Invalid username or password")))
            .filter(user -> user.getEnabled())
            .switchIfEmpty(Mono.error(new DisabledException("User is disabled talk to admin to enable your account")))
            .filter(user -> passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
            .map(user -> {
                return LoginResponseDto.builder()
                    .user(userMapper.toDto(user))
                    .jwt(jwtProvider.generateToken(user))
                    .build();
            })
            .switchIfEmpty(Mono.error(new BadCredentialsException("Invalid username or password")))
            ;
    }

}
