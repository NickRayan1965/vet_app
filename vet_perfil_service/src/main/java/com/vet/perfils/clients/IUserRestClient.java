package com.vet.perfils.clients;
import com.vet.commons.dtos.res.UserDto;

import reactor.core.publisher.Mono;

public interface IUserRestClient {
    Mono<UserDto> findOneById(Long id);
}
