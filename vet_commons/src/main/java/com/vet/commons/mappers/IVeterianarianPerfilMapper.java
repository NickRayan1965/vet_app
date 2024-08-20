package com.vet.commons.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.vet.commons.dtos.req.CreateVeterinarianPerfilDto;
import com.vet.commons.dtos.res.VeterinarianPerfilDto;
import com.vet.commons.entities.mongo.VeterinarianPerfil;

@Mapper(componentModel = "spring")
public interface IVeterianarianPerfilMapper {

    default VeterinarianPerfil dtoToEntity(CreateVeterinarianPerfilDto dto) {
        VeterinarianPerfil entity = new VeterinarianPerfil();
        mergeToEntity(dto, entity);
        return entity;
    }

    default VeterinarianPerfilDto toDto(VeterinarianPerfil entity) {
        VeterinarianPerfilDto dto = new VeterinarianPerfilDto();
        setEntityOnDto(entity, dto);
        return dto;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clinic.id", source = "clinicId")
    @Mapping(target = "enabled", constant = "true")
    void mergeToEntity(CreateVeterinarianPerfilDto dto, @MappingTarget VeterinarianPerfil entity);

    void setEntityOnDto(VeterinarianPerfil entity, @MappingTarget VeterinarianPerfilDto dto);
}
