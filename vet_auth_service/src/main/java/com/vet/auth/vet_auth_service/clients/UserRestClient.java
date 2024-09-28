package com.vet.auth.vet_auth_service.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vet.auth_common.entities.User;
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
    public Mono<User> findByUsername(String username) {
        return webClientResponseHandler.handlerMonoResponse(userWebClient.get().uri("/users/username/{username}", username).retrieve(), User.class);
    }
    
}
