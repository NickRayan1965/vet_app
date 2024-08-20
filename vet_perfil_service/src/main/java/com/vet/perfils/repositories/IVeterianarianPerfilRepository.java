package com.vet.perfils.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.vet.commons.entities.mongo.VeterinarianPerfil;

public interface IVeterianarianPerfilRepository extends ReactiveMongoRepository<VeterinarianPerfil, String>{
    
}
