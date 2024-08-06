package br.com.fiap.hackaton.clientes.adapters;

import br.com.fiap.hackaton.clientes.entities.ClienteEntity;
import br.com.fiap.hackaton.clientes.records.DadosCriacaoClienteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClienteAdapterTest {

    ClienteAdapter clienteAdapter = new ClienteAdapter();

    @Test
    void testConverterParaEntity() {
        ClienteEntity result = clienteAdapter.converterParaEntity(new DadosCriacaoClienteDTO("nome", "cpf", "email", "telefone", "rua", "cidade", "estado", "cep", "pais"));
        Assertions.assertEquals(new ClienteEntity("cpf", "nome", "email", "telefone", "rua", "cidade", "estado", "cep", "pais"), result);
    }
}
