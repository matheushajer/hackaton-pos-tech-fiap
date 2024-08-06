package br.com.fiap.hackaton.clientes.exceptions;

import br.com.fiap.hackaton.clientes.controllers.TestController;
import br.com.fiap.hackaton.clientes.security.TestSecurityConfig;
import br.com.fiap.hackaton.clientes.security.TokenService;
import br.com.fiap.hackaton.clientes.useCases.RegistrarClienteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TestController.class)
@Import({CustomExceptionHandler.class, TestSecurityConfig.class})
public class CustomExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrarClienteUseCase registrarClienteUseCase;

    @MockBean
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        // Any setup if needed
    }

    @Test
    public void testHandleIllegalArgumentExceptions() throws Exception {
        mockMvc.perform(get("/test/illegal-argument")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("Test IllegalArgumentException"))
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    public void testHandleEntityNotFoundExceptions() throws Exception {
        mockMvc.perform(get("/test/entity-not-found")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.mensagem").value("Test EntityNotFoundException"))
                .andExpect(jsonPath("$.status").value(500));
    }
}
