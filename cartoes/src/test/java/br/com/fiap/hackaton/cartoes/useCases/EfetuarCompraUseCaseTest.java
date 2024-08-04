package br.com.fiap.hackaton.cartoes.useCases;

import br.com.fiap.hackaton.cartoes.entities.CartaoEntity;
import br.com.fiap.hackaton.cartoes.exceptions.PaymentRequiredException;
import br.com.fiap.hackaton.cartoes.records.DadosEfetuarCompraDTO;
import br.com.fiap.hackaton.cartoes.repositories.CartaoRepository;
import br.com.fiap.hackaton.cartoes.useCases.utils.EfetuarPagamentoService;
import br.com.fiap.hackaton.cartoes.useCases.utils.ValidarDadosCartaoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class EfetuarCompraUseCaseTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private ValidarDadosCartaoService validarDadosCartao;

    @Mock
    private EfetuarPagamentoService efetuarPagamentoService;

    @InjectMocks
    private EfetuarCompraUseCase efetuarCompraUseCase;

    public EfetuarCompraUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEfetuarPagamento_ValidData_ReturnsTrue() throws AccessDeniedException {
        // Arrange
        DadosEfetuarCompraDTO dados = new DadosEfetuarCompraDTO(
                "12345678901", "1234567812345678", "12/25", "123", new BigDecimal("100.00")
        );
        CartaoEntity cartao = new CartaoEntity("12345678901", new BigDecimal("1000.00"), "1234567812345678", "12/25", "123");
        List<CartaoEntity> cartoes = Arrays.asList(cartao);

        when(cartaoRepository.findByCpf(dados.cpf())).thenReturn(cartoes);
        when(validarDadosCartao.validarDadosDoCartao(cartoes, dados)).thenReturn(1L);

        // Act
        boolean result = efetuarCompraUseCase.efetuarPagamento(dados);

        // Assert
        assertTrue(result);
        verify(efetuarPagamentoService, times(1)).efetuarPagamento(1L, dados);
    }

    @Test
    void testEfetuarPagamento_NegativeValue_ThrowsIllegalArgumentException() {
        // Arrange
        DadosEfetuarCompraDTO dados = new DadosEfetuarCompraDTO(
                "12345678901", "1234567812345678", "12/25", "123", new BigDecimal("-100.00")
        );

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> efetuarCompraUseCase.efetuarPagamento(dados));
    }

    @Test
    void testEfetuarPagamento_InvalidCardData_ThrowsAccessDeniedException() throws AccessDeniedException {
        // Arrange
        DadosEfetuarCompraDTO dados = new DadosEfetuarCompraDTO(
                "12345678901", "1234567812345678", "12/25", "123", new BigDecimal("100.00")
        );
        CartaoEntity cartao = new CartaoEntity("12345678901", new BigDecimal("1000.00"), "1234567812345678", "12/25", "123");
        List<CartaoEntity> cartoes = Arrays.asList(cartao);

        when(cartaoRepository.findByCpf(dados.cpf())).thenReturn(cartoes);
        when(validarDadosCartao.validarDadosDoCartao(cartoes, dados)).thenThrow(new AccessDeniedException("Dados inválidos"));

        // Act & Assert
        assertThrows(AccessDeniedException.class, () -> efetuarCompraUseCase.efetuarPagamento(dados));
    }

    @Test
    void testEfetuarPagamento_ValidData_ThrowsPaymentRequiredException() throws AccessDeniedException {
        // Arrange
        DadosEfetuarCompraDTO dados = new DadosEfetuarCompraDTO(
                "12345678901", "1234567812345678", "12/25", "123", new BigDecimal("100.00")
        );
        CartaoEntity cartao = new CartaoEntity("12345678901", new BigDecimal("100.00"), "1234567812345678", "12/25", "123");
        List<CartaoEntity> cartoes = Arrays.asList(cartao);

        when(cartaoRepository.findByCpf(dados.cpf())).thenReturn(cartoes);
        when(validarDadosCartao.validarDadosDoCartao(cartoes, dados)).thenReturn(1L);
        doThrow(new PaymentRequiredException("Compra Recusada! O valor da compra é maior que o limite do cartão"))
                .when(efetuarPagamentoService).efetuarPagamento(1L, dados);

        // Act & Assert
        assertThrows(PaymentRequiredException.class, () -> efetuarCompraUseCase.efetuarPagamento(dados));
    }
}
