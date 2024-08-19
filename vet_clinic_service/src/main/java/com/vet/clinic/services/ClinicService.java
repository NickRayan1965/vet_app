package com.vet.clinic.services;

import org.springframework.stereotype.Service;

import com.vet.commons.dtos.ClinicDto;
import com.vet.clinic.dtos.CreateClinicDto;
import com.vet.clinic.dtos.UpdateClinicDto;
import com.vet.clinic.models.mappers.IClinicMapper;
import com.vet.clinic.repositories.IClinicRepository;
import com.vet.commons.entities.mongo.Clinic;
import com.vet.commons.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClinicService implements IClinicService {

    private final IClinicRepository clinicRepository;

    private final IClinicMapper clinicMapper;

    @Override
    public Mono<ClinicDto> create(CreateClinicDto createClinicDto) {
        return Mono
            .just(createClinicDto)
            .map(clinicMapper::dtoToEntity)
            .flatMap(clinicRepository::save)
            .map(clinicMapper::toDto);
            
    }

    @Override
    public Mono<ClinicDto> update(String id, UpdateClinicDto updateClinicDto) {
        return findOneById(id)
            .map(clinicMapper::dtoToEntity)
            .flatMap(clinic -> {
                return getDtoVerified(clinicMapper.dtoToEntity(updateClinicDto))
                .map(c -> clinic);
            })
            .doOnNext(clinic -> {
                clinicMapper.mergeToEntity(updateClinicDto, clinic);
            })
            .flatMap(clinicRepository::save)
            .map(clinicMapper::toDto);
    }

    @Override
    public Mono<ClinicDto> findOneById(String id) {
        return clinicRepository
                .findById(id)
                .map(clinicMapper::toDto)
                .switchIfEmpty(Mono.error(new NotFoundException("Clinic not found")))
                ;
                
    }

    @Override
    public Mono<Void> delete(String id) {
        return findOneById(id)
            .map(clinicMapper::dtoToEntity)
            .flatMap(clinic -> {
                clinic.setEnabled(false);
                return clinicRepository.save(clinic);
            })
            .then();
    }

    @Override
    public Flux<ClinicDto> findAll() {
        return clinicRepository.findAll().map(clinicMapper::toDto);
    }

    @Override
    public Mono<Clinic> getDtoVerified(Clinic dto) {
        return Mono.just(dto);
    }

}
