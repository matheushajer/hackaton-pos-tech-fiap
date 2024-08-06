package br.com.fiap.hackaton.clientes.records;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DadosCriacaoClienteDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public DadosCriacaoClienteDTOTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testValidData() {
        DadosCriacaoClienteDTO dto = new DadosCriacaoClienteDTO(
                "João Silva", "12345678901", "joao@exemplo.com", "11987654321",
                "Rua Exemplo", "Cidade Exemplo", "Estado Exemplo", "12345678", "Brasil"
        );

        Set<ConstraintViolation<DadosCriacaoClienteDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Não deve haver violações de validação");
    }

    @Test
    public void testInvalidData() {
        DadosCriacaoClienteDTO dto = new DadosCriacaoClienteDTO(
                "", "", "", "",
                "", "", "", "", ""
        );

        Set<ConstraintViolation<DadosCriacaoClienteDTO>> violations = validator.validate(dto);
        assertEquals(10, violations.size(), "Deve haver 8 violações de validação");

        // Verificar as mensagens específicas de erro
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("É Obrigatório informar o nome do cliente")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("É Obrigatório informar o cpf do cliente")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("É Obrigatório informar o e-mail do cliente")));

    }

    @Test
    public void testCreateDto() {
        DadosCriacaoClienteDTO dto = new DadosCriacaoClienteDTO(
                "João Silva", "12345678901", "joao@exemplo.com", "11987654321",
                "Rua Exemplo", "Cidade Exemplo", "Estado Exemplo", "12345678", "Brasil"
        );

        assertEquals("João Silva", dto.nome());
        assertEquals("12345678901", dto.cpf());
        assertEquals("joao@exemplo.com", dto.email());
        assertEquals("11987654321", dto.telefone());
        assertEquals("Rua Exemplo", dto.rua());
        assertEquals("Cidade Exemplo", dto.cidade());
        assertEquals("Estado Exemplo", dto.estado());
        assertEquals("12345678", dto.cep());
        assertEquals("Brasil", dto.pais());
    }

}
