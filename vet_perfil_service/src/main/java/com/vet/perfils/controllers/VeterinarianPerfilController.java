package com.vet.perfils.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class VeterinarianPerfilController {
    @GetMapping()
    public Flux<Object> test() {
        System.out.println("id");
        return null;
    }
    
}
