package br.com.fiap.hackaton.cartoes.useCases.utils;

import br.com.fiap.hackaton.cartoes.entities.CartaoEntity;
import br.com.fiap.hackaton.cartoes.exceptions.PaymentRequiredException;
import br.com.fiap.hackaton.cartoes.records.DadosEfetuarCompraDTO;
import br.com.fiap.hackaton.cartoes.repositories.CartaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EfetuarPagamentoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private EfetuarPagamentoService efetuarPagamentoService;

    public EfetuarPagamentoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveEfetuarPagamentoComSucesso() {
        // Arrange
        CartaoEntity cartaoEntity = new CartaoEntity();
        cartaoEntity.setId(1L);
        cartaoEntity.setLimite(BigDecimal.valueOf(500.00));
        cartaoEntity.setNumero("1234567812345678");
        cartaoEntity.setDataValidade("12/25");
        cartaoEntity.setCvv("123");

        when(cartaoRepository.findById(1L)).thenReturn(Optional.of(cartaoEntity));

        DadosEfetuarCompraDTO dadosCompra = new DadosEfetuarCompraDTO(
                "1234567812345678",
                "1234567812345678",
                "12/25",
                "123",
                BigDecimal.valueOf(100.00)
        );

        // Act
        efetuarPagamentoService.efetuarPagamento(1L, dadosCompra);

        // Assert
        verify(cartaoRepository, times(1)).save(cartaoEntity);
        assertEquals(BigDecimal.valueOf(400.00), cartaoEntity.getLimite());
    }

    @Test
    void deveLancarExceptionQuandoLimiteInsuficiente() {
        // Arrange
        CartaoEntity cartaoEntity = new CartaoEntity();
        cartaoEntity.setId(1L);
        cartaoEntity.setLimite(BigDecimal.valueOf(50.00));
        cartaoEntity.setNumero("1234567812345678");
        cartaoEntity.setDataValidade("12/25");
        cartaoEntity.setCvv("123");

        when(cartaoRepository.findById(1L)).thenReturn(Optional.of(cartaoEntity));

        DadosEfetuarCompraDTO dadosCompra = new DadosEfetuarCompraDTO(
                "1234567812345678",
                "1234567812345678",
                "12/25",
                "123",
                BigDecimal.valueOf(100.00)
        );

        // Act & Assert
        PaymentRequiredException thrown = assertThrows(PaymentRequiredException.class, () ->
                efetuarPagamentoService.efetuarPagamento(1L, dadosCompra)
        );
        assertEquals("Compra Recusada! O valor da compra é maior que o limite do cartão", thrown.getMessage());
    }

    @Test
    void deveLancarExceptionQuandoCartaoNaoEncontrado() {
        // Arrange
        when(cartaoRepository.findById(1L)).thenReturn(Optional.empty());

        DadosEfetuarCompraDTO dadosCompra = new DadosEfetuarCompraDTO(
                "1234567812345678",
                "1234567812345678",
                "12/25",
                "123",
                BigDecimal.valueOf(100.00)
        );

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                efetuarPagamentoService.efetuarPagamento(1L, dadosCompra)
        );
        assertEquals("Cartão não encontrado", thrown.getMessage());
    }

}
