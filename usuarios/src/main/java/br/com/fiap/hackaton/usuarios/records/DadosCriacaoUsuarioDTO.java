package br.com.fiap.hackaton.usuarios.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Classe DTO para travegar os dados de criação de um usuário pela API.
 *
 * @param login
 * @param senha
 */
public record DadosCriacaoUsuarioDTO(

        @NotBlank(message = "O login do usuário é obrigatório!")
        @Size(min = 5, message = "O login deve conter no minimo 5 caracteres!")
        String login,

        @NotBlank(message = "A senha do usuário é obrigatória!")
        String senha
) {
}
