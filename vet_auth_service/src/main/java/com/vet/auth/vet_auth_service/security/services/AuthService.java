package com.vet.auth.vet_auth_service.security.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vet.auth.vet_auth_service.clients.IUserRestClient;
import com.vet.auth.vet_auth_service.dtos.LoginDto;
import com.vet.auth.vet_auth_service.dtos.LoginResponseDto;
import com.vet.auth.vet_auth_service.dtos.RefreshTokenDto;
import com.vet.auth.vet_auth_service.security.jwt.JwtProvider;
import com.vet.auth_common.mappers.IUserMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final PasswordEncoder passwordEncoder;

    private final IUserRestClient userRestClient;

    private final IUserMapper userMapper;
    
    private final JwtProvider jwtProvider;

    @Override
    public Mono<LoginResponseDto> login(LoginDto loginDto) {
        return userRestClient.findByUsername(loginDto.getUsername())
            .filter(user -> user.getEnabled())
            .switchIfEmpty(Mono.error(new DisabledException("User is disabled")))
            .filter(user -> passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
            .switchIfEmpty(Mono.error(new BadCredentialsException("Invalid username or password")))
            .map(user -> {
                return LoginResponseDto.builder()
                    .user(userMapper.toDto(user))
                    .token(jwtProvider.generateJwt(user))
                    .refreshToken(jwtProvider.generateRefreshToken(user))
                    .build();  
            });

    }

    @Override
    public Mono<LoginResponseDto> refresToken(RefreshTokenDto refreshTokenDto) {
        return Mono.just(jwtProvider.getSubject(refreshTokenDto.getRefreshToken()))
            .flatMap(userRestClient::findByUsername)
            .map(user -> {
                return LoginResponseDto.builder()
                    .user(userMapper.toDto(user))
                    .token(jwtProvider.generateJwt(user))
                    .refreshToken(jwtProvider.generateRefreshToken(user))
                    .build();
            })
            ;
    }
    
}
