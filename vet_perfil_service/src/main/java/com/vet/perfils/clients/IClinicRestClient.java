package com.vet.perfils.clients;

import com.vet.commons.dtos.ClinicDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClinicRestClient {
    Mono<ClinicDto> create();
    Mono<ClinicDto> findOneById(String id);
    Flux<ClinicDto> findAll();
    Mono<ClinicDto> update(String id);
}
