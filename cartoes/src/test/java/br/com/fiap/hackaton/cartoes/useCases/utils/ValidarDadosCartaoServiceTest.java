package br.com.fiap.hackaton.cartoes.useCases.utils;

import br.com.fiap.hackaton.cartoes.entities.CartaoEntity;
import br.com.fiap.hackaton.cartoes.records.DadosEfetuarCompraDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidarDadosCartaoServiceTest {

    @Test
    void testValidarDadosDoCartao_ValidData_ReturnsCardId() throws AccessDeniedException {
        // Arrange
        ValidarDadosCartaoService service = new ValidarDadosCartaoService();
        List<CartaoEntity> cartoes = Arrays.asList(
                new CartaoEntity("12345678901", new BigDecimal("1000.00"), "1234567812345678", "12/25", "123"),
                new CartaoEntity("98765432100", new BigDecimal("500.00"), "8765432187654321", "11/24", "456")
        );
        DadosEfetuarCompraDTO dados = new DadosEfetuarCompraDTO(
                "12345678901", "1234567812345678", "12/25", "123", new BigDecimal("100.00")
        );

        // Act
        Long id = service.validarDadosDoCartao(cartoes, dados);

        // Assert
        assertEquals(1L, id); // Assume o primeiro cartão tem id 1, altere conforme sua lógica
    }

    @Test
    void testValidarDadosDoCartao_InvalidData_ThrowsAccessDeniedException() {
        // Arrange
        ValidarDadosCartaoService service = new ValidarDadosCartaoService();
        List<CartaoEntity> cartoes = Arrays.asList(
                new CartaoEntity("12345678901", new BigDecimal("1000.00"), "1234567812345678", "12/25", "123")
        );
        DadosEfetuarCompraDTO dados = new DadosEfetuarCompraDTO(
                "12345678901", "1234567812345678", "12/26", "123", new BigDecimal("100.00")
        );

        // Act & Assert
        assertThrows(AccessDeniedException.class, () -> service.validarDadosDoCartao(cartoes, dados));
    }
}
