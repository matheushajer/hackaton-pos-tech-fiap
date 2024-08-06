package br.com.fiap.hackaton.clientes.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClienteEntityTest {

    private ClienteEntity cliente;

    @BeforeEach
    public void setUp() {
        cliente = new ClienteEntity("12345678900", "John Doe", "john.doe@example.com", "123456789",
                "Rua Exemplo", "Cidade Exemplo", "Estado Exemplo", "12345678", "Brasil");
    }

    @Test
    public void testClienteEntityNotNull() {
        assertNotNull(cliente);
    }

    @Test
    public void testGetters() {
        assertEquals("12345678900", cliente.getCpf());
        assertEquals("John Doe", cliente.getNome());
        assertEquals("john.doe@example.com", cliente.getEmail());
        assertEquals("123456789", cliente.getTelefone());
        assertEquals("Rua Exemplo", cliente.getRua());
        assertEquals("Cidade Exemplo", cliente.getCidade());
        assertEquals("Estado Exemplo", cliente.getEstado());
        assertEquals("12345678", cliente.getCep());
        assertEquals("Brasil", cliente.getPais());
    }

    @Test
    public void testSetters() {
        cliente.setCpf("09876543210");
        cliente.setNome("Jane Doe");
        cliente.setEmail("jane.doe@example.com");
        cliente.setTelefone("987654321");
        cliente.setRua("Nova Rua");
        cliente.setCidade("Nova Cidade");
        cliente.setEstado("Novo Estado");
        cliente.setCep("87654321");
        cliente.setPais("Nova Pais");

        assertEquals("09876543210", cliente.getCpf());
        assertEquals("Jane Doe", cliente.getNome());
        assertEquals("jane.doe@example.com", cliente.getEmail());
        assertEquals("987654321", cliente.getTelefone());
        assertEquals("Nova Rua", cliente.getRua());
        assertEquals("Nova Cidade", cliente.getCidade());
        assertEquals("Novo Estado", cliente.getEstado());
        assertEquals("87654321", cliente.getCep());
        assertEquals("Nova Pais", cliente.getPais());
    }
}
