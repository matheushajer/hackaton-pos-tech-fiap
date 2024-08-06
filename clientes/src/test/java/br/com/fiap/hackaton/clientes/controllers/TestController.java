package br.com.fiap.hackaton.clientes.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/illegal-argument")
    public void throwIllegalArgumentException() {
        throw new IllegalArgumentException("Test IllegalArgumentException");
    }

    @GetMapping("/entity-not-found")
    public void throwEntityNotFoundException() {
        throw new EntityNotFoundException("Test EntityNotFoundException");
    }
}
