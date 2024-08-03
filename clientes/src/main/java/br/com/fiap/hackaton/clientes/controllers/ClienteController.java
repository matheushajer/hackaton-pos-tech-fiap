package br.com.fiap.hackaton.clientes.controllers;

import br.com.fiap.hackaton.clientes.records.DadosCriacaoClienteDTO;
import br.com.fiap.hackaton.clientes.useCases.RegistrarClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe controller das operações do cliente.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    RegistrarClienteUseCase registrarClienteUseCase;

    @PostMapping("/cliente")
    public ResponseEntity<String> registrarCliente(@RequestBody @Validated DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        return ResponseEntity.ok(registrarClienteUseCase.registrarCliente(dadosCriacaoClienteDTO));

    }


}
