package br.com.fiap.hackaton.pagamentos.records;

import br.com.fiap.hackaton.pagamentos.entities.enuns.MetodoPagamentoEnum;
import br.com.fiap.hackaton.pagamentos.entities.enuns.StatusPagamentoEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DadosConsultaPagamentoPorCpfTest {

    @Test
    public void testDadosConsultaPagamentoPorCpf() {
        // Dados para o teste
        BigDecimal valor = new BigDecimal("150.00");
        String descricao = "Compra em loja";
        MetodoPagamentoEnum metodoPagamento = MetodoPagamentoEnum.CARTAO_CREDITO; // Exemplo de valor, ajuste conforme sua enumeração
        StatusPagamentoEnum status = StatusPagamentoEnum.APROVADO; // Exemplo de valor, ajuste conforme sua enumeração

        // Criação do objeto
        DadosConsultaPagamentoPorCpf dto = new DadosConsultaPagamentoPorCpf(
                valor,
                descricao,
                metodoPagamento,
                status
        );

        // Validação dos valores
        assertEquals(valor, dto.valor(), "O valor deve ser igual ao esperado");
        assertEquals(descricao, dto.descricao(), "A descrição deve ser igual à esperada");
        assertEquals(metodoPagamento, dto.metodo_pagamento(), "O método de pagamento deve ser igual ao esperado");
        assertEquals(status, dto.status(), "O status deve ser igual ao esperado");
    }

    @Test
    public void testDadosConsultaPagamentoPorCpfWithDifferentValues() {
        // Dados para o teste
        BigDecimal valor = new BigDecimal("200.00");
        String descricao = "Compra online";
        MetodoPagamentoEnum metodoPagamento = MetodoPagamentoEnum.CARTAO_CREDITO; // Exemplo de valor, ajuste conforme sua enumeração
        StatusPagamentoEnum status = StatusPagamentoEnum.APROVADO; // Exemplo de valor, ajuste conforme sua enumeração

        // Criação do objeto
        DadosConsultaPagamentoPorCpf dto = new DadosConsultaPagamentoPorCpf(
                valor,
                descricao,
                metodoPagamento,
                status
        );

        // Validação dos valores
        assertEquals(valor, dto.valor(), "O valor deve ser igual ao esperado");
        assertEquals(descricao, dto.descricao(), "A descrição deve ser igual à esperada");
        assertEquals(metodoPagamento, dto.metodo_pagamento(), "O método de pagamento deve ser igual ao esperado");
        assertEquals(status, dto.status(), "O status deve ser igual ao esperado");
    }
}
