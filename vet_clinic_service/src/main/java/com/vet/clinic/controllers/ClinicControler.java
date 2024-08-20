package com.vet.clinic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.clinic.services.IClinicService;
import com.vet.commons.dtos.req.CreateClinicDto;
import com.vet.commons.dtos.req.UpdateClinicDto;
import com.vet.commons.dtos.res.ClinicDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clinic")
@RequiredArgsConstructor
public class ClinicControler {

    private final IClinicService clinicService;

    @PostMapping
    public Mono<ClinicDto> create(@RequestBody CreateClinicDto createClinicDto) {
        return clinicService.create(createClinicDto);
    }

    @GetMapping
    public Flux<ClinicDto> findAll() {
        return clinicService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ClinicDto> findOneId(@PathVariable String id) {
        return clinicService.findOneById(id);
    }

    @PutMapping("/{id}")
    public Mono<ClinicDto> update(@PathVariable String id, @RequestBody UpdateClinicDto updateClinicDto) {
        return clinicService.update(id, updateClinicDto);
    }
}
