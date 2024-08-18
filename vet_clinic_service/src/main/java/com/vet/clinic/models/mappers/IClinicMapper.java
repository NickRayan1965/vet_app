package com.vet.clinic.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.vet.clinic.dtos.ClinicDto;
import com.vet.clinic.dtos.CreateClinicDto;
import com.vet.clinic.dtos.UpdateClinicDto;
import com.vet.commons.entities.mongo.Clinic;

@Mapper(componentModel = "spring")
public interface IClinicMapper {

    default Clinic dtoToEntity(CreateClinicDto dto) {
        Clinic entity = new Clinic();
        mergeToEntity(dto, entity);
        return entity;
    }

    default Clinic dtoToEntity(ClinicDto dto) {
        Clinic entity = new Clinic();
        setDtoOnEntity(dto, entity);
        return entity;
    }
    default ClinicDto toDto(Clinic entity) {
        ClinicDto dto = new ClinicDto();
        setEntityOnDto(entity, dto);
        return dto;
    }


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    void mergeToEntity(CreateClinicDto dto, @MappingTarget Clinic entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    void mergeToEntity(UpdateClinicDto dto, @MappingTarget Clinic entity);

    void setEntityOnDto(Clinic entity, @MappingTarget ClinicDto dto);

    void setDtoOnEntity(ClinicDto dto, @MappingTarget Clinic entity);
}
