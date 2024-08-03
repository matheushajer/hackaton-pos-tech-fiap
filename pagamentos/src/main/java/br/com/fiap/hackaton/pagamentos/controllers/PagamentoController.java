package br.com.fiap.hackaton.pagamentos.controllers;

import br.com.fiap.hackaton.pagamentos.records.DadosConsultaPagamentoPorCpf;
import br.com.fiap.hackaton.pagamentos.records.DadosCriacaoPagamentoDTO;
import br.com.fiap.hackaton.pagamentos.useCases.ConsultaDePagamentosPorClienteUseCase;
import br.com.fiap.hackaton.pagamentos.useCases.CriarPagamentoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Classe controller das operações de pagamento.
 */
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    CriarPagamentoUseCase criarPagamentoUseCase;
    @Autowired
    ConsultaDePagamentosPorClienteUseCase consultaDePagamentosPorClienteUseCase;

    @PostMapping
    public ResponseEntity<String> criarPedidoDePagamento(
            @RequestBody @Validated DadosCriacaoPagamentoDTO dadosCriacaoPagamentoDTO) {

        return ResponseEntity.ok(criarPagamentoUseCase.criarRegistroDePagamento(dadosCriacaoPagamentoDTO));

    }

    @GetMapping("cliente/{cpf}")
    public ResponseEntity<Page<DadosConsultaPagamentoPorCpf>> consultarPagamentosPorCpf(
            @PathVariable String cpf, Pageable pageable) {

        return ResponseEntity.ok(consultaDePagamentosPorClienteUseCase.consultarPagamentosPorCliente(cpf, pageable));

    }

}
