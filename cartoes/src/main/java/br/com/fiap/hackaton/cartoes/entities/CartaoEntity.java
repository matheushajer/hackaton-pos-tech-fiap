package br.com.fiap.hackaton.cartoes.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Classe para representar uma entidade cartão no sistema.
 */
@Entity
@Data
@Table(name = "tb_cartoes")
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String cpf;
    BigDecimal limite;
    String numero;
    String dataValidade;
    String cvv;

    // **************
    // Construtores
    // **************

    public CartaoEntity() {
    }

    public CartaoEntity(String cpf, BigDecimal limite, String numero, String dataValidade, String cvv) {
        this.cpf = cpf;
        this.limite = limite;
        this.numero = numero;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
    }

}
