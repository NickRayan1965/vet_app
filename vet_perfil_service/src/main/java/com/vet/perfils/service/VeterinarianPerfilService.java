package com.vet.perfils.service;

import org.springframework.stereotype.Service;

import com.vet.commons.dtos.req.CreateVeterinarianPerfilDto;
import com.vet.commons.dtos.res.ClinicDto;
import com.vet.commons.dtos.res.VeterinarianPerfilDto;
import com.vet.commons.entities.mongo.Clinic;
import com.vet.commons.entities.mongo.VeterinarianPerfil;
import com.vet.commons.exceptions.NotFoundException;
import com.vet.commons.mappers.IClinicMapper;
import com.vet.commons.mappers.IVeterianarianPerfilMapper;
import com.vet.perfils.clients.IClinicRestClient;
import com.vet.perfils.clients.IUserRestClient;
import com.vet.perfils.repositories.IVeterianarianPerfilRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class VeterinarianPerfilService implements IVeterinarianPerfilService {

    private final IClinicRestClient clinicRestClient;
    private final IUserRestClient userRestClient;
    private final IClinicMapper clinicMapper;
    private final IVeterianarianPerfilMapper veterianarianPerfilMapper;
    private final IVeterianarianPerfilRepository veterianarianPerfilRepository;

    @Override
    public Mono<VeterinarianPerfilDto> create(CreateVeterinarianPerfilDto createVeterinarianPerfilDto) {
        return Mono.just(veterianarianPerfilMapper.dtoToEntity(createVeterinarianPerfilDto))
        .flatMap(this::getDtoVerified)
        .flatMap(veterianarianPerfilRepository::save)
        .map(veterianarianPerfilMapper::toDto);
    }

    @Override
    public Mono<VeterinarianPerfilDto> findOneById(String id) {
        return veterianarianPerfilRepository.findById(id).map(veterianarianPerfilMapper::toDto).switchIfEmpty(Mono.error(new NotFoundException("Veterinarian Perfil not found")));
    }

    @Override
    public Flux<VeterinarianPerfilDto> findAll() {
        return veterianarianPerfilRepository.findAll().map(veterianarianPerfilMapper::toDto);
    }

    @Override
    public Mono<VeterinarianPerfilDto> update(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Mono<VeterinarianPerfil> getDtoVerified(VeterinarianPerfil veterinarianPerfil) {
        Long userId = veterinarianPerfil.getUserId();
        String clinicId = veterinarianPerfil.getClinic().getId();

        Mono<Clinic> clinicPromise = clinicId != null ? clinicRestClient.findOneById(clinicId).map(clinicMapper::dtoToEntity) : Mono.empty();
        Mono<Void> userPromise = userId != null ? userRestClient.findOneById(userId).then() : Mono.empty();

        return Flux.merge(userPromise, clinicPromise)
            .collectList()
            .map( list -> {
                ClinicDto clinicDto = (ClinicDto) list.get(1);
                veterinarianPerfil.setClinic(clinicMapper.dtoToEntity(clinicDto));
                return veterinarianPerfil;
            });
    }
    
}
