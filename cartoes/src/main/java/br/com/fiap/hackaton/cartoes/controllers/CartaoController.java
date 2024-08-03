package br.com.fiap.hackaton.cartoes.controllers;

import br.com.fiap.hackaton.cartoes.records.DadosCriacaoCartaoDTO;
import br.com.fiap.hackaton.cartoes.records.DadosEfetuarCompraDTO;
import br.com.fiap.hackaton.cartoes.useCases.EfetuarCompraUseCase;
import br.com.fiap.hackaton.cartoes.useCases.GerarCartaoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

/**
 * Classe controller das operações de cartão.
 */
@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    GerarCartaoUseCase gerarCartaoUseCase;
    @Autowired
    EfetuarCompraUseCase efetuarPagamentoUseCase;

    @PostMapping("/cartao")
    public ResponseEntity gerarCartao(@RequestBody @Validated DadosCriacaoCartaoDTO dadosCriacaoCartaoDTO) throws AccessDeniedException {

        gerarCartaoUseCase.gerarCartao(dadosCriacaoCartaoDTO);

        return ResponseEntity.ok().build();

    }

    @PutMapping("/efetuar-compra")
    public ResponseEntity<Boolean> efetuarCompra(@RequestBody @Validated DadosEfetuarCompraDTO dadosEfetuarCompraDTO) throws AccessDeniedException {

        return ResponseEntity.ok(efetuarPagamentoUseCase.efetuarPagamento(dadosEfetuarCompraDTO));

    }


}
