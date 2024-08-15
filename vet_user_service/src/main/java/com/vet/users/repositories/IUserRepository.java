package com.vet.users.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.vet.users.entities.User;

public interface IUserRepository extends R2dbcRepository<User, Long>{
    
}
