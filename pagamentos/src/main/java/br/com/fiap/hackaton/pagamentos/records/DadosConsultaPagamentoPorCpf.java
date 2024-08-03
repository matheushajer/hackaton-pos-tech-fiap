package br.com.fiap.hackaton.pagamentos.records;

import br.com.fiap.hackaton.pagamentos.entities.enuns.MetodoPagamentoEnum;
import br.com.fiap.hackaton.pagamentos.entities.enuns.StatusPagamentoEnum;

import java.math.BigDecimal;

/**
 * Classe DTO para retornar os dados da API de consulta de pagamentos por cliente.
 *
 * @param valor
 * @param descricao
 * @param metodo_pagamento
 * @param status
 */
public record DadosConsultaPagamentoPorCpf(
        BigDecimal valor,
        String descricao,
        MetodoPagamentoEnum metodo_pagamento,
        StatusPagamentoEnum status

) {
}
