package br.com.fiap.hackaton.cartoes.controllers;

import br.com.fiap.hackaton.cartoes.records.DadosCriacaoCartaoDTO;
import br.com.fiap.hackaton.cartoes.useCases.GerarCartaoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

/**
 * Classe controller das operações de cartão.
 */
@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    GerarCartaoUseCase gerarCartaoUseCase;

    @PostMapping("/cartao")
    public ResponseEntity gerarCartao(@RequestBody @Validated DadosCriacaoCartaoDTO dadosCriacaoCartaoDTO) throws AccessDeniedException {

        gerarCartaoUseCase.gerarCartao(dadosCriacaoCartaoDTO);

        return ResponseEntity.ok().build();

    }


}
