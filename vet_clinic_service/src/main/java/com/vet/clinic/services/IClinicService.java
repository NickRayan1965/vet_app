package com.vet.clinic.services;

import com.vet.commons.dtos.req.CreateClinicDto;
import com.vet.commons.dtos.req.UpdateClinicDto;
import com.vet.commons.dtos.res.ClinicDto;
import com.vet.commons.entities.mongo.Clinic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClinicService {
    Mono<ClinicDto> create(CreateClinicDto createClinicDto);
    Mono<ClinicDto> update(String id, UpdateClinicDto updateClinicDto);
    Mono<ClinicDto> findOneById(String id);
    Mono<Void> delete(String id);
    Flux<ClinicDto> findAll();
    Mono<Clinic> getDtoVerified(Clinic dto);
}
