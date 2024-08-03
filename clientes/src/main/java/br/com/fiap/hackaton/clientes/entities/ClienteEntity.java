package br.com.fiap.hackaton.clientes.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Classe para representação da Entidade Cliente.
 */
@Entity
@Data
@Table(name = "tb_clientes")
public class ClienteEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String cpf;
    String nome;
    String email;
    String telefone;
    String rua;
    String cidade;
    String estado;
    String cep;
    String pais;

    // **************
    // Construtores
    // **************

    public ClienteEntity() {
    }

    public ClienteEntity(String cpf, String nome, String email, String telefone, String rua, String cidade, String estado,
                         String cep, String pais) {

        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.pais = pais;

    }

}
