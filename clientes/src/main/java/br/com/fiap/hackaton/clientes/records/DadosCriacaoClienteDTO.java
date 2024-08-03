package br.com.fiap.hackaton.clientes.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Classe DTO para representar os dados vindos pela API POST /clientes
 * para criação de uma entidade cliente.
 *
 * @param nome
 * @param email
 * @param telefone
 * @param rua
 * @param cidade
 * @param estado
 * @param cep
 * @param pais
 */
public record DadosCriacaoClienteDTO(

        @NotBlank(message = "É Obrigatório informar o nome do cliente")
        String nome,
        @NotBlank(message = "É Obrigatório informar o cpf do cliente")
        @Size(min = 11, max = 11, message = "O CPF deve conter 11 digitos")
        String cpf,
        @NotBlank(message = "É Obrigatório informar o e-mail do cliente")
        String email,
        @NotBlank(message = "É Obrigatório informar o telefone do cliente")
        String telefone,
        @NotBlank(message = "É Obrigatório informar a rua do cliente")
        String rua,
        @NotBlank(message = "É Obrigatório informar a cidade do cliente")
        String cidade,
        @NotBlank(message = "É Obrigatório informar o estado do cliente")
        String estado,
        @NotBlank(message = "É Obrigatório informar o cep do cliente")
        String cep,
        @NotBlank(message = "É Obrigatório informar o pais do cliente")
        String pais
) {
}
