package br.com.fiap.hackaton.cartoes.useCases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;

import br.com.fiap.hackaton.cartoes.adapters.CartaoAdapter;
import br.com.fiap.hackaton.cartoes.entities.CartaoEntity;
import br.com.fiap.hackaton.cartoes.records.DadosCriacaoCartaoDTO;
import br.com.fiap.hackaton.cartoes.repositories.CartaoRepository;
import br.com.fiap.hackaton.cartoes.useCases.utils.ValidarSeExisteClienteService;
import br.com.fiap.hackaton.cartoes.useCases.utils.ValidarSePodeCriarCartaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GerarCartaoUseCaseTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private CartaoAdapter cartaoAdapter;

    @Mock
    private ValidarSeExisteClienteService validarSeExisteClienteService;

    @Mock
    private ValidarSePodeCriarCartaoService validarSePodeCriarCartaoService;

    @InjectMocks
    private GerarCartaoUseCase gerarCartaoUseCase;

    private DadosCriacaoCartaoDTO dadosCriacaoCartaoDTO;

    @BeforeEach
    public void setUp() {
        dadosCriacaoCartaoDTO = new DadosCriacaoCartaoDTO("12345678901", new BigDecimal("5000.00"),
                "1234567812345678", "12/25", "123");
    }

    @Test
    public void testGerarCartao_Sucesso() throws AccessDeniedException {
        CartaoEntity cartaoEntity = new CartaoEntity("12345678901", new BigDecimal("5000.00"),
                "1234567812345678", "12/25", "123");

        when(cartaoAdapter.converterParaEntity(dadosCriacaoCartaoDTO)).thenReturn(cartaoEntity);

        gerarCartaoUseCase.gerarCartao(dadosCriacaoCartaoDTO);

        verify(validarSeExisteClienteService, times(1)).existeClienteParaCpf(dadosCriacaoCartaoDTO.cpf());
        verify(validarSePodeCriarCartaoService, times(1)).validarSePodeCriarCartao(dadosCriacaoCartaoDTO.cpf(),
                dadosCriacaoCartaoDTO.limite());
        verify(cartaoRepository, times(1)).save(cartaoEntity);
    }

    @Test
    public void testGerarCartao_ClienteNaoExiste() {
        doThrow(new IllegalArgumentException("CPF não pertence a nenhum cliente cadastrado"))
                .when(validarSeExisteClienteService).existeClienteParaCpf(dadosCriacaoCartaoDTO.cpf());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gerarCartaoUseCase.gerarCartao(dadosCriacaoCartaoDTO);
        });

        assertEquals("CPF não pertence a nenhum cliente cadastrado", exception.getMessage());
    }

    @Test
    public void testGerarCartao_ClienteJaPossuiDoisCartoes() throws AccessDeniedException {
        doThrow(new AccessDeniedException("CPF informado já possui 2 cartões cadastrados"))
                .when(validarSePodeCriarCartaoService).validarSePodeCriarCartao(dadosCriacaoCartaoDTO.cpf(),
                        dadosCriacaoCartaoDTO.limite());

        Exception exception = assertThrows(AccessDeniedException.class, () -> {
            gerarCartaoUseCase.gerarCartao(dadosCriacaoCartaoDTO);
        });

        assertEquals("CPF informado já possui 2 cartões cadastrados", exception.getMessage());
    }

    @Test
    public void testGerarCartao_LimiteNegativo() throws AccessDeniedException {
        dadosCriacaoCartaoDTO = new DadosCriacaoCartaoDTO("12345678901", new BigDecimal("-500.00"),
                "1234567812345678", "12/25", "123");

        doThrow(new IllegalArgumentException("O limite do cartão não pode ser criado com valor negativo"))
                .when(validarSePodeCriarCartaoService).validarSePodeCriarCartao(dadosCriacaoCartaoDTO.cpf(),
                        dadosCriacaoCartaoDTO.limite());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gerarCartaoUseCase.gerarCartao(dadosCriacaoCartaoDTO);
        });

        assertEquals("O limite do cartão não pode ser criado com valor negativo", exception.getMessage());
    }
}
