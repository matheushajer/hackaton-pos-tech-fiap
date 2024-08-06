package br.com.fiap.hackaton.pagamentos.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;

public class TokenServiceTest {

    private TokenService tokenService;

    @BeforeEach
    public void setUp() {
        // Inicializa o TokenService
        tokenService = new TokenService();
        // Define o valor da senha_tokens diretamente usando reflexão
        try {
            java.lang.reflect.Field field = TokenService.class.getDeclaredField("senha_tokens");
            field.setAccessible(true);
            field.set(tokenService, "secret");
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível definir o valor de senha_tokens", e);
        }
    }

    @Test
    public void testGerarToken() {
        String token = tokenService.gerarToken();
        assertNotNull(token);
    }

    @Test
    public void testGetSubject_ValidToken() {
        // Cria um token com subject
        String token = JWT.create()
                .withIssuer("Hackaton Autentication APIs")
                .withSubject("subject")
                .sign(Algorithm.HMAC256("secret"));

        String subject = tokenService.getSubject(token);
        assertEquals("subject", subject);
    }

    @Test
    public void testGetSubject_InvalidToken() {
        assertThrows(BadCredentialsException.class, () -> {
            tokenService.getSubject("invalid_token");
        });
    }
}
