package com.vet.perfils.clients;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vet.commons.dtos.ClinicDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClinicRestClient implements IClinicRestClient{

    @Qualifier("clinicWebClient")
    private final WebClient cliniWebClient;
    @Override
    public Mono<ClinicDto> create() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Mono<ClinicDto> findOneById(String id) {
        return cliniWebClient
            .get()
            .uri("/clinic/{id}", id)
            .retrieve()
            .bodyToMono(ClinicDto.class);
    }

    @Override
    public Flux<ClinicDto> findAll() {
        return cliniWebClient
            .get()
            .uri("/clinic")
            .retrieve()
            .bodyToFlux(ClinicDto.class);
    }

    @Override
    public Mono<ClinicDto> update(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
