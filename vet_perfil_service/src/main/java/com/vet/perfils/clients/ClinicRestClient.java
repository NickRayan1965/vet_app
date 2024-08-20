package com.vet.perfils.clients;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vet.commons.dtos.ClinicDto;
import com.vet.commons.util.WebClientResponseHandler;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClinicRestClient implements IClinicRestClient {

    @Qualifier("clinicWebClient")
    private final WebClient cliniWebClient;

    private final WebClientResponseHandler webClientResponseHandler;

    @Override
    public Mono<ClinicDto> findOneById(String id) {
        return webClientResponseHandler.handlerMonoResponse(cliniWebClient
            .get()
            .uri("/clinic/{id}", id)
            .retrieve(), ClinicDto.class);
    }    
}
