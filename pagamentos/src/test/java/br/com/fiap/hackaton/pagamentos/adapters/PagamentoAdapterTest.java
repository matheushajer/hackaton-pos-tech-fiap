package br.com.fiap.hackaton.pagamentos.adapters;

import br.com.fiap.hackaton.pagamentos.entities.PagamentoEntity;
import br.com.fiap.hackaton.pagamentos.entities.enuns.MetodoPagamentoEnum;
import br.com.fiap.hackaton.pagamentos.entities.enuns.StatusPagamentoEnum;
import br.com.fiap.hackaton.pagamentos.records.DadosConsultaPagamentoPorCpf;
import br.com.fiap.hackaton.pagamentos.records.DadosCriacaoPagamentoDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentoAdapterTest {

    @Test
    public void testConverterParaEntity() {
        // Arrange
        PagamentoAdapter adapter = new PagamentoAdapter();
        DadosCriacaoPagamentoDTO dto = new DadosCriacaoPagamentoDTO(
                "12345678901",
                "1234567812345678",
                "12/34",
                "123",
                BigDecimal.valueOf(100.00)
        );

        // Act
        PagamentoEntity entity = adapter.converterParaEntity(dto);

        // Assert
        assertEquals(dto.cpf(), entity.getCpf());
        assertEquals(dto.numero(), entity.getNumero());
        assertEquals(dto.data_validade(), entity.getDataValidade());
        assertEquals(dto.cvv(), entity.getCvv());
        assertEquals(dto.valor(), entity.getValorCompra());
    }

    @Test
    public void testConverterParaDadosConsultaPorCpf() {
        // Arrange
        PagamentoAdapter adapter = new PagamentoAdapter();
        PagamentoEntity entity = new PagamentoEntity(
                "12345678901",
                "1234567812345678",
                "12/34",
                "123",
                BigDecimal.valueOf(100.00)
        );
        entity.setMetodoPagamento(MetodoPagamentoEnum.CARTAO_CREDITO);
        entity.setStatusPagamento(StatusPagamentoEnum.APROVADO);
        entity.setDescricao("Pagamento de teste");

        // Act
        DadosConsultaPagamentoPorCpf dto = adapter.converterParaDadosConsultaPorCpf(entity);

        // Assert
        assertEquals(entity.getValorCompra(), dto.valor());
        assertEquals(entity.getDescricao(), dto.descricao());
        assertEquals(entity.getMetodoPagamento(), dto.metodo_pagamento());
        assertEquals(entity.getStatusPagamento(), dto.status());
    }

}
