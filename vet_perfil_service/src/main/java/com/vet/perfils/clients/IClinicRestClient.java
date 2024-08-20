package com.vet.perfils.clients;

import com.vet.commons.dtos.ClinicDto;

import reactor.core.publisher.Mono;

public interface IClinicRestClient {
    Mono<ClinicDto> findOneById(String id);
}
