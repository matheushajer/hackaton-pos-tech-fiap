package br.com.fiap.hackaton.cartoes.useCases.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.hackaton.cartoes.http.ClienteClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidarSeExisteClienteServiceTest {

    @Mock
    private ClienteClient clienteClient;

    @InjectMocks
    private ValidarSeExisteClienteService validarSeExisteClienteService;

    @Test
    public void testExisteClienteParaCpf_Sucesso() {
        String cpf = "12345678901";

        when(clienteClient.existeCliente(cpf)).thenReturn(true);

        assertDoesNotThrow(() -> validarSeExisteClienteService.existeClienteParaCpf(cpf));
    }

    @Test
    public void testExisteClienteParaCpf_Falha() {
        String cpf = "12345678901";

        when(clienteClient.existeCliente(cpf)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validarSeExisteClienteService.existeClienteParaCpf(cpf);
        });

        assertEquals("CPF não pertence a nenhum cliente cadastrado, é preciso criar um cliente com o CPF informado.", exception.getMessage());
    }
}
