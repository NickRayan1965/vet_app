package com.vet.perfils.service;

import com.vet.commons.dtos.req.CreateVeterinarianPerfilDto;
import com.vet.commons.dtos.res.VeterinarianPerfilDto;
import com.vet.commons.entities.mongo.VeterinarianPerfil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IVeterinarianPerfilService {
    Mono<VeterinarianPerfilDto> create(CreateVeterinarianPerfilDto createVeterinarianPerfilDto);
    Mono<VeterinarianPerfilDto> findOneById(String id);
    Flux<VeterinarianPerfilDto> findAll();
    Mono<VeterinarianPerfilDto> update(String id);
    Mono<VeterinarianPerfil> getDtoVerified(VeterinarianPerfil veterinarianPerfil);
}