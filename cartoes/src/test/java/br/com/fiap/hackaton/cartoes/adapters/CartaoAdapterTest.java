package br.com.fiap.hackaton.cartoes.adapters;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import br.com.fiap.hackaton.cartoes.entities.CartaoEntity;
import br.com.fiap.hackaton.cartoes.records.DadosCriacaoCartaoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartaoAdapterTest {

    private CartaoAdapter cartaoAdapter;

    @BeforeEach
    public void setUp() {
        cartaoAdapter = new CartaoAdapter();
    }

    @Test
    public void testConverterParaEntity() {
        DadosCriacaoCartaoDTO dadosCriacaoCartaoDTO = new DadosCriacaoCartaoDTO(
                "12345678901",
                new BigDecimal("5000.00"),
                "1234567812345678",
                "12/25",
                "123"
        );

        CartaoEntity cartaoEntity = cartaoAdapter.converterParaEntity(dadosCriacaoCartaoDTO);

        assertNotNull(cartaoEntity);
        assertEquals("12345678901", cartaoEntity.getCpf());
        assertEquals(new BigDecimal("5000.00"), cartaoEntity.getLimite());
        assertEquals("1234567812345678", cartaoEntity.getNumero());
        assertEquals("12/25", cartaoEntity.getDataValidade());
        assertEquals("123", cartaoEntity.getCvv());
    }
}
