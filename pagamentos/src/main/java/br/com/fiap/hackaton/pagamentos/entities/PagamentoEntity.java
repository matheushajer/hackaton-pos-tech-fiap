package br.com.fiap.hackaton.pagamentos.entities;

import br.com.fiap.hackaton.pagamentos.entities.enuns.MetodoPagamentoEnum;
import br.com.fiap.hackaton.pagamentos.entities.enuns.StatusPagamentoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "tb_pagamentos")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String cpf;
    String numero;
    String dataValidade;
    String cvv;
    BigDecimal valorCompra;

    // Dado chumbado, pois no requisito, foi solicitado apenas a devolutiva desse dado, nunca a criação.
    String descricao = "Compra do produto X";

    // Dado chumbado, pois no requisito, foi solicitado apenas a devolutiva desse dado, nunca a criação.
    @Enumerated(EnumType.STRING)
    MetodoPagamentoEnum metodoPagamento = MetodoPagamentoEnum.CARTAO_CREDITO;

    @Enumerated(EnumType.STRING)
    StatusPagamentoEnum statusPagamento;


    // **************
    // Construtores
    // **************

    public PagamentoEntity() {
    }

    public PagamentoEntity(String cpf, String numero, String dataValidade, String cvv, BigDecimal valorCompra) {
        this.cpf = cpf;
        this.numero = numero;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
        this.valorCompra = valorCompra;
    }
}
