package com.vet.auth.vet_auth_service.clients;

import com.vet.auth.vet_auth_service.entities.User;
import reactor.core.publisher.Mono;

public interface IUserRestClient {
    Mono<User> findByUsername(String username);
}
