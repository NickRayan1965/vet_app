package com.vet.users.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vet.auth_common.dtos.CreateUserDto;
import com.vet.auth_common.dtos.UpdateUserDto;
import com.vet.auth_common.entities.User;
import com.vet.auth_common.mappers.IUserMapper;
import com.vet.commons.dtos.req.FindUsersQueryDto;
import com.vet.commons.dtos.res.FindAllResultDto;
import com.vet.commons.dtos.res.UserDto;
import com.vet.commons.exceptions.NotFoundException;
import com.vet.users.repositories.IUserRepository;
import com.vet.users.repositories.UserCustomRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final UserCustomRepository userCustomRepository;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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
    public Mono<FindAllResultDto<UserDto>> findAll(FindUsersQueryDto queryDto) {
        Integer page = queryDto.getPage();
        Integer pageSize = queryDto.getPageSize();
        return userCustomRepository.advancedPagedSearchAndCount(null, pageSize, (page - 1) * pageSize)
            .map(result -> {
                FindAllResultDto<UserDto> response = new FindAllResultDto<>();
                response.setCount(result.getT1());
                response.setData(result.getT2().stream().map(userMapper::toDto).toList());
                return response;
            })
        ;
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
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findOneByUsername(username)
            .switchIfEmpty(Mono.error(new BadCredentialsException("Invalid username or password")))
            .cast(UserDetails.class)
            ;
    }

}
