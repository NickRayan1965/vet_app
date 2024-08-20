package com.vet.perfils.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.commons.dtos.req.CreateVeterinarianPerfilDto;

import com.vet.commons.dtos.res.VeterinarianPerfilDto;
import com.vet.perfils.service.IVeterinarianPerfilService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/veterinarian-perfil")
@RequiredArgsConstructor
public class VeterinarianPerfilController {

    private final IVeterinarianPerfilService veterinarianPerfilService;
    @PostMapping
    Mono<VeterinarianPerfilDto> create(@RequestBody CreateVeterinarianPerfilDto createVeterinarianPerfilDto) {
        return veterinarianPerfilService.create(createVeterinarianPerfilDto);
    }
    
}
