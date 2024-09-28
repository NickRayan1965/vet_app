package com.vet.auth.vet_auth_service.clients;

import com.vet.auth_common.entities.User;

import reactor.core.publisher.Mono;

public interface IUserRestClient {
    Mono<User> findByUsername(String username);
}
