package com.vet.auth.vet_auth_service.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.auth.vet_auth_service.dtos.LoginDto;
import com.vet.auth.vet_auth_service.dtos.LoginResponseDto;
import com.vet.auth.vet_auth_service.dtos.RefreshTokenDto;
import com.vet.auth.vet_auth_service.security.services.IAuthService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/login")
    Mono<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @PostMapping("/refresh-token")
    public Mono<LoginResponseDto> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return authService.refresToken(refreshTokenDto);
    }
    
}
