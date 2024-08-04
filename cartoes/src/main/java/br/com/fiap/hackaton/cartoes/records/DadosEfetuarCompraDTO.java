package br.com.fiap.hackaton.cartoes.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * Classe DTO para manipular os dados de uma tentativa de compra.
 * É usado pela API fornecida ao serviço de pagamentos.
 *
 * @param cpf
 * @param numero
 * @param data_validade
 * @param cvv
 * @param valor
 */
public record DadosEfetuarCompraDTO(

        @NotBlank(message = "O CPF do cliente é obrigatório")
        @Size(min = 11, max = 11, message = "O CPF deve conter 11 digitos")
        String cpf,

        @NotBlank(message = "O numero do cartão é obrigatório")
        @Size(min = 16, max = 16, message = "O número do cartão deve  conter 16 digitos")
        String numero,

        @NotBlank(message = "O data de validade do cartão é obrigatório")
        @Size(min = 5, max = 5, message = "A data de validade deve ser inserida no formato MM/YY")
        String data_validade,

        @NotBlank(message = "O código de segurança do cartão é obrigatório")
        @Size(min = 3, max = 3, message = "Código de segurança deve conter 3 digitos")
        String cvv,

        @NotNull(message = "Valor da compra é obrigatório")
        BigDecimal valor
) {
}
