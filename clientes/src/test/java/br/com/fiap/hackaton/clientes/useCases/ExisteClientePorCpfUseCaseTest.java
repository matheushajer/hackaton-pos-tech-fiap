package br.com.fiap.hackaton.clientes.useCases;

import br.com.fiap.hackaton.clientes.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ExisteClientePorCpfUseCaseTest {

    @InjectMocks
    private ExisteClientePorCpfUseCase existeClientePorCpfUseCase;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExisteClienteByCpf_Success() {
        String cpf = "12345678900";
        when(clienteRepository.existsByCpf(cpf)).thenReturn(true);

        boolean resultado = existeClientePorCpfUseCase.existeClieteByCpf(cpf);

        assertTrue(resultado);
    }

    @Test
    public void testExisteClienteByCpf_NotFound() {
        String cpf = "12345678900";
        when(clienteRepository.existsByCpf(cpf)).thenReturn(false);

        boolean resultado = existeClientePorCpfUseCase.existeClieteByCpf(cpf);

        assertFalse(resultado);
    }

    @Test
    public void testExisteClienteByCpf_EmptyCpf() {
        String cpf = "";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            existeClientePorCpfUseCase.existeClieteByCpf(cpf);
        });

        assertTrue(exception.getMessage().contains("O CPF informado está vazio ou nulo."));
    }

    @Test
    public void testExisteClienteByCpf_NullCpf() {
        String cpf = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            existeClientePorCpfUseCase.existeClieteByCpf(cpf);
        });

        assertTrue(exception.getMessage().contains("O CPF informado está vazio ou nulo."));
    }
}
