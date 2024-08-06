package br.com.fiap.hackaton.pagamentos.records;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DadosCriacaoPagamentoDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidDadosCriacaoPagamentoDTO() {
        DadosCriacaoPagamentoDTO dto = new DadosCriacaoPagamentoDTO(
                "12345678901",
                "1234567812345678",
                "12/24",
                "123",
                new BigDecimal("100.00")
        );

        Set<jakarta.validation.ConstraintViolation<DadosCriacaoPagamentoDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Não deve haver violações de validação");
    }

    @Test
    public void testInvalidCpf() {
        DadosCriacaoPagamentoDTO dto = new DadosCriacaoPagamentoDTO(
                "1234567890", // CPF inválido
                "1234567812345678",
                "12/24",
                "123",
                new BigDecimal("100.00")
        );

        Set<jakarta.validation.ConstraintViolation<DadosCriacaoPagamentoDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação para o CPF");
        assertEquals("O CPF deve conter 11 digitos", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidNumero() {
        DadosCriacaoPagamentoDTO dto = new DadosCriacaoPagamentoDTO(
                "12345678901",
                "123456781234567", // Número do cartão inválido
                "12/24",
                "123",
                new BigDecimal("100.00")
        );

        Set<jakarta.validation.ConstraintViolation<DadosCriacaoPagamentoDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação para o número do cartão");
        assertEquals("O número do cartão deve  conter 16 digitos", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidDataValidade() {
        DadosCriacaoPagamentoDTO dto = new DadosCriacaoPagamentoDTO(
                "12345678901",
                "1234567812345678",
                "12/2024", // Data de validade inválida
                "123",
                new BigDecimal("100.00")
        );

        Set<jakarta.validation.ConstraintViolation<DadosCriacaoPagamentoDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação para a data de validade");
        assertEquals("A data de validade deve ser inserida no formato MM/YY", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidCvv() {
        DadosCriacaoPagamentoDTO dto = new DadosCriacaoPagamentoDTO(
                "12345678901",
                "1234567812345678",
                "12/24",
                "12", // CVV inválido
                new BigDecimal("100.00")
        );

        Set<jakarta.validation.ConstraintViolation<DadosCriacaoPagamentoDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação para o CVV");
        assertEquals("Código de segurança deve conter 3 digitos", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidValor() {
        DadosCriacaoPagamentoDTO dto = new DadosCriacaoPagamentoDTO(
                "12345678901",
                "1234567812345678",
                "12/24",
                "123",
                null // Valor nulo
        );

        Set<jakarta.validation.ConstraintViolation<DadosCriacaoPagamentoDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação para o valor");
        assertEquals("Valor da compra é obrigatório", violations.iterator().next().getMessage());
    }
}
