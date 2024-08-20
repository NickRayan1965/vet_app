package com.vet.commons.util;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.vet.commons.exceptions.Exception;
import com.vet.commons.exceptions.ThrowableRestExeption;

@Component
public class WebClientResponseHandler {
    public <T> Mono<T> handlerMonoResponse(ResponseSpec clientResponse, Class<T> responseType) {
        return clientResponse
            .onStatus(HttpStatusCode::is4xxClientError, this::handleError)
            .onStatus(HttpStatusCode::is5xxServerError, this::handleError)
            .bodyToMono(responseType);
    }

    public <T> Flux<T> handlerFluxResponse(ResponseSpec clientResponse, Class<T> responseType) {
        return clientResponse
            .onStatus(HttpStatusCode::is4xxClientError, this::handleError)
            .onStatus(HttpStatusCode::is5xxServerError, this::handleError)
            .bodyToFlux(responseType);
    }
    private Mono<Throwable> handleError(ClientResponse  clientResponse) {
        return clientResponse.bodyToMono(Exception.class)
            .flatMap(ex -> Mono.error(new ThrowableRestExeption(ex)));
    }
    
}
