package br.com.fiap.hackaton.cartoes.useCases.utils;

import br.com.fiap.hackaton.cartoes.repositories.CartaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidarSePodeCriarCartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private ValidarSePodeCriarCartaoService validarSePodeCriarCartaoService;

    @Test
    public void testValidarSePodeCriarCartao_Sucesso() {
        String cpf = "12345678901";
        BigDecimal limite = new BigDecimal("5000.00");

        when(cartaoRepository.countByCpf(cpf)).thenReturn(1L);

        assertDoesNotThrow(() -> validarSePodeCriarCartaoService.validarSePodeCriarCartao(cpf, limite));
    }

    @Test
    public void testValidarSePodeCriarCartao_CpfComDoisCartoes() {
        String cpf = "12345678901";
        BigDecimal limite = new BigDecimal("5000.00");

        when(cartaoRepository.countByCpf(cpf)).thenReturn(2L);

        Exception exception = assertThrows(AccessDeniedException.class, () -> {
            validarSePodeCriarCartaoService.validarSePodeCriarCartao(cpf, limite);
        });

        assertEquals("CPF informado já possui 2 cartões cadastrados", exception.getMessage());
    }

    @Test
    public void testValidarSePodeCriarCartao_LimiteNegativo() {
        String cpf = "12345678901";
        BigDecimal limite = new BigDecimal("-100.00");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validarSePodeCriarCartaoService.validarSePodeCriarCartao(cpf, limite);
        });

        assertEquals("O limite do cartão não pode ser criado com valor negativo", exception.getMessage());
    }
}
