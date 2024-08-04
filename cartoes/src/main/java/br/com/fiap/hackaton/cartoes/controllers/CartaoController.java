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
 * Classe controller das operações do serviço cartoes.
 */
@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    GerarCartaoUseCase gerarCartaoUseCase;
    @Autowired
    EfetuarCompraUseCase efetuarPagamentoUseCase;

    /**
     * Endpoint para recibimento da solicitação de criação de um novo cartão no sistema.
     *
     * @param dadosCriacaoCartaoDTO Objeto com os dados para criação do cartão.
     * @return Retorna apens status 200 - OK.
     * @throws AccessDeniedException Exception lançada caso regra a regra de negicio de limite de cartões
     *                               por cliente seja quebrada.
     */
    @PostMapping("/cartao")
    public ResponseEntity gerarCartao(@RequestBody @Validated DadosCriacaoCartaoDTO dadosCriacaoCartaoDTO) throws AccessDeniedException {

        gerarCartaoUseCase.gerarCartao(dadosCriacaoCartaoDTO);

        return ResponseEntity.ok().build();

    }

    /**
     * Endpoint exclusivo para o serviço de pagamentos, efetua a validação de limite e aprovação da compra.
     *
     * @param dadosEfetuarCompraDTO Objeto com os dados para pagamento.
     * @return Retorna true or false caso o pagamento tenha sido efetuado com sucesso.
     * @throws AccessDeniedException Exception lanaçada caso o cartão informado não tenha limite para pagamento.
     */
    @PutMapping("/efetuar-compra")
    public ResponseEntity<Boolean> efetuarCompra(@RequestBody @Validated DadosEfetuarCompraDTO dadosEfetuarCompraDTO) throws AccessDeniedException {

        return ResponseEntity.ok(efetuarPagamentoUseCase.efetuarPagamento(dadosEfetuarCompraDTO));

    }


}
