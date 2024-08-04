package br.com.fiap.hackaton.cartoes.controller;

import br.com.fiap.hackaton.cartoes.controllers.CartaoController;
import br.com.fiap.hackaton.cartoes.records.DadosCriacaoCartaoDTO;
import br.com.fiap.hackaton.cartoes.records.DadosEfetuarCompraDTO;
import br.com.fiap.hackaton.cartoes.useCases.EfetuarCompraUseCase;
import br.com.fiap.hackaton.cartoes.useCases.GerarCartaoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;

import static org.mockito.Mockito.*;

@WebMvcTest(CartaoController.class)
public class CartaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GerarCartaoUseCase gerarCartaoUseCase;

    @MockBean
    private EfetuarCompraUseCase efetuarCompraUseCase;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGerarCartao() throws Exception {
        DadosCriacaoCartaoDTO dadosCriacaoCartaoDTO = new DadosCriacaoCartaoDTO(
                "12345678901",
                new BigDecimal("1000.00"),
                "1234567812345678",
                "12/25",
                "123"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/cartoes/cartao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dadosCriacaoCartaoDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(gerarCartaoUseCase, times(1)).gerarCartao(dadosCriacaoCartaoDTO);
    }

    @Test
    public void testEfetuarCompra() throws Exception {
        DadosEfetuarCompraDTO dadosEfetuarCompraDTO = new DadosEfetuarCompraDTO(
                "12345678901",
                "1234567812345678",
                "12/25",
                "123",
                new BigDecimal("100.00")
        );

        when(efetuarCompraUseCase.efetuarPagamento(dadosEfetuarCompraDTO)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/cartoes/efetuar-compra")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dadosEfetuarCompraDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        verify(efetuarCompraUseCase, times(1)).efetuarPagamento(dadosEfetuarCompraDTO);
    }


    @Test
    public void testEfetuarCompra_ShouldThrowException() throws Exception {
        DadosEfetuarCompraDTO dadosEfetuarCompraDTO = new DadosEfetuarCompraDTO(
                "12345678901",
                "1234567812345678",
                "12/25",
                "123",
                new BigDecimal("100.00")
        );

        when(efetuarCompraUseCase.efetuarPagamento(dadosEfetuarCompraDTO))
                .thenThrow(new AccessDeniedException("Pagamento negado"));

        mockMvc.perform(MockMvcRequestBuilders.put("/cartoes/efetuar-compra")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dadosEfetuarCompraDTO)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

        verify(efetuarCompraUseCase, times(1)).efetuarPagamento(dadosEfetuarCompraDTO);
    }
}
