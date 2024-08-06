package br.com.fiap.hackaton.pagamentos.exceptions;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomErrorResponseTest {

    @Test
    public void testCustomErrorResponseWithAllFields() {
        LocalDateTime timestamp = LocalDateTime.now();
        String campo = "campo";
        String mensagem = "mensagem";
        int status = 400;

        CustomErrorResponse response = new CustomErrorResponse(timestamp, campo, mensagem, status);

        assertEquals(timestamp, response.getTimestamp());
        assertEquals(campo, response.getCampo());
        assertEquals(mensagem, response.getMensagem());
        assertEquals(status, response.getStatus());
    }

    @Test
    public void testCustomErrorResponseWithMessageAndStatus() {
        LocalDateTime timestamp = LocalDateTime.now();
        String mensagem = "mensagem";
        int status = 400;

        CustomErrorResponse response = new CustomErrorResponse(timestamp, mensagem, status);

        assertEquals(timestamp, response.getTimestamp());
        assertEquals(mensagem, response.getMensagem());
        assertEquals(status, response.getStatus());
    }

    @Test
    public void testCustomErrorResponseWithMessageOnly() {
        LocalDateTime timestamp = LocalDateTime.now();
        String mensagem = "mensagem";

        CustomErrorResponse response = new CustomErrorResponse(timestamp, mensagem);

        assertEquals(timestamp, response.getTimestamp());
        assertEquals(mensagem, response.getMensagem());
        assertEquals(0, response.getStatus()); // Default status is not set
    }
}
