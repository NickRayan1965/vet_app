package com.vet.perfils.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vet.commons.dtos.res.UserDto;
import com.vet.commons.util.WebClientResponseHandler;

import reactor.core.publisher.Mono;

@Service
public class UserRestClient implements IUserRestClient {

    @Autowired
    @Qualifier("userWebClient")
    private WebClient userWebClient;

    @Autowired
    private WebClientResponseHandler webClientResponseHandler;


    @Override
    public Mono<UserDto> findOneById(Long id) {
        return webClientResponseHandler.handlerMonoResponse(userWebClient.get().uri("/users/{id}", id).retrieve(), UserDto.class);
    }

}
