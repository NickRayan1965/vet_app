package com.vet.perfils.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.commons.dtos.ClinicDto;
import com.vet.perfils.clients.IClinicRestClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class VeterinarianPerfilController {

    private final IClinicRestClient clinicRestClient;

    @GetMapping()
    public Flux<ClinicDto> test() {
        return clinicRestClient.findAll();
    }
    
}
