package br.com.fiap.hackaton.clientes.useCases;

import br.com.fiap.hackaton.clientes.adapters.ClienteAdapter;
import br.com.fiap.hackaton.clientes.entities.ClienteEntity;
import br.com.fiap.hackaton.clientes.records.DadosCriacaoClienteDTO;
import br.com.fiap.hackaton.clientes.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RegistrarClienteUseCaseTest {

    @InjectMocks
    private RegistrarClienteUseCase registrarClienteUseCase;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteAdapter clienteAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrarCliente_Success() {
        DadosCriacaoClienteDTO dados = new DadosCriacaoClienteDTO("12345678900", "John Doe", "john.doe@example.com",
                "123456789", "Rua Exemplo", "Cidade Exemplo", "Estado Exemplo", "12345678", "Brasil");

        ClienteEntity clienteEntity = new ClienteEntity(dados.cpf(), dados.nome(), dados.email(), dados.telefone(),
                dados.rua(), dados.cidade(), dados.estado(), dados.cep(), dados.pais());
        clienteEntity.setId(1L);

        when(clienteRepository.existsByCpf(dados.cpf())).thenReturn(false);
        when(clienteAdapter.converterParaEntity(dados)).thenReturn(clienteEntity);
        when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);

        String resultado = registrarClienteUseCase.registrarCliente(dados);

        assertEquals("id_cliente: 1", resultado);

        verify(clienteRepository).existsByCpf(dados.cpf());
        verify(clienteAdapter).converterParaEntity(dados);
        verify(clienteRepository).save(clienteEntity);
    }

    @Test
    public void testRegistrarCliente_CpfJaCadastrado() {
        DadosCriacaoClienteDTO dados = new DadosCriacaoClienteDTO("12345678900", "John Doe", "john.doe@example.com",
                "123456789", "Rua Exemplo", "Cidade Exemplo", "Estado Exemplo", "12345678", "Brasil");

        when(clienteRepository.existsByCpf(dados.cpf())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            registrarClienteUseCase.registrarCliente(dados);
        });

        assertEquals("CPF já cadastrado! Não é possivel informar o mesmo CPF para dois clientes", exception.getMessage());

        verify(clienteRepository).existsByCpf(dados.cpf());
        verify(clienteAdapter, never()).converterParaEntity(dados);
        verify(clienteRepository, never()).save(any(ClienteEntity.class));
    }
}
