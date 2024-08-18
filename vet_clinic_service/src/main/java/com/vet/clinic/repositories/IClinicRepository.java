package com.vet.clinic.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.vet.commons.entities.mongo.Clinic;


@Repository
public interface IClinicRepository extends ReactiveMongoRepository<Clinic, String>{
    
}
