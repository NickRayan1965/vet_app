package com.vet.auth.vet_auth_service.security.services;

import com.vet.auth.vet_auth_service.dtos.LoginDto;
import com.vet.auth.vet_auth_service.dtos.LoginResponseDto;
import com.vet.auth.vet_auth_service.dtos.RefreshTokenDto;

import reactor.core.publisher.Mono;

public interface IAuthService {
    Mono<LoginResponseDto> login(LoginDto loginDto);
    Mono<LoginResponseDto> refresToken(RefreshTokenDto refreshTokenDto);
}
