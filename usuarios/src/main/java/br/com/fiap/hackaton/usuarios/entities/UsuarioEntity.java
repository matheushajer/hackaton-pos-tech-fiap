package br.com.fiap.hackaton.usuarios.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Classe para reprenser um usuário do sistema.
 */
@Entity
@Data
@Table(name = "tb_usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String login;
    String senha;

    // **************
    // Construtores
    // **************

    public UsuarioEntity() {
    }

    public UsuarioEntity(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

}
