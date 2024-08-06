package br.com.fiap.hackaton.clientes.controllers;

import br.com.fiap.hackaton.clientes.records.DadosCriacaoClienteDTO;
import br.com.fiap.hackaton.clientes.security.TestSecurityConfig;
import br.com.fiap.hackaton.clientes.security.TokenService;
import br.com.fiap.hackaton.clientes.useCases.ExisteClientePorCpfUseCase;
import br.com.fiap.hackaton.clientes.useCases.RegistrarClienteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
@Import(TestSecurityConfig.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrarClienteUseCase registrarClienteUseCase;

    @MockBean
    private ExisteClientePorCpfUseCase existeClientePorCpfUseCase;

    @MockBean
    private TokenService tokenService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    public void testExisteCliente() throws Exception {
        String cpf = "12345678901";
        when(existeClientePorCpfUseCase.existeClieteByCpf(cpf)).thenReturn(true);

        mockMvc.perform(get("/clientes/existe-cliente/{cpf}", cpf))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testRegistrarCliente() throws Exception {
        DadosCriacaoClienteDTO dados = new DadosCriacaoClienteDTO("Nome", "12345678901", "email@example.com", "123456789", "Rua", "Cidade", "Estado", "12345678", "Pais");
        when(registrarClienteUseCase.registrarCliente(any(DadosCriacaoClienteDTO.class))).thenReturn("Cliente registrado com sucesso");

        mockMvc.perform(post("/clientes/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Nome\", \"cpf\": \"12345678901\", \"email\": \"email@example.com\", \"telefone\": \"123456789\", \"rua\": \"Rua\", \"cidade\": \"Cidade\", \"estado\": \"Estado\", \"cep\": \"12345678\", \"pais\": \"Pais\" }"))
                .andExpect(status().isOk());
    }

}
