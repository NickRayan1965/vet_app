package com.vet.users.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.vet.auth_common.entities.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserRepository extends R2dbcRepository<User, Long>{
    @Query("select * from users where enabled = true")
    Flux<User> findAllEnabled();    
    Mono<User> findOneByUsername(String username);
}
