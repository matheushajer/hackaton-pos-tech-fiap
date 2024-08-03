package br.com.fiap.hackaton.clientes.controllers;

import br.com.fiap.hackaton.clientes.records.DadosCriacaoClienteDTO;
import br.com.fiap.hackaton.clientes.useCases.ExisteClientePorCpfUseCase;
import br.com.fiap.hackaton.clientes.useCases.RegistrarClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Classe controller das operações do cliente.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    RegistrarClienteUseCase registrarClienteUseCase;
    @Autowired
    ExisteClientePorCpfUseCase existeClientePorCpfUseCase;


    @GetMapping("/existe-cliente/{cpf}")
    public ResponseEntity<Boolean> existeCliente(@PathVariable String cpf) {

        return ResponseEntity.ok(existeClientePorCpfUseCase.existeClieteByCpf(cpf));

    }

    @PostMapping("/cliente")
    public ResponseEntity<String> registrarCliente(@RequestBody @Validated DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        return ResponseEntity.ok(registrarClienteUseCase.registrarCliente(dadosCriacaoClienteDTO));

    }
}
