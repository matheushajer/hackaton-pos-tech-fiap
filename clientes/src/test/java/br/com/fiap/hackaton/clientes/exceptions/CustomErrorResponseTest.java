package br.com.fiap.hackaton.clientes.exceptions;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomErrorResponseTest {

    @Test
    public void testCustomErrorResponseWithCampo() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.now();
        String campo = "campo";
        String mensagem = "mensagem";
        int status = 400;

        // Act
        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, campo, mensagem, status);

        // Assert
        assertThat(errorResponse.getTimestamp()).isEqualTo(timestamp);
        assertThat(errorResponse.getCampo()).isEqualTo(campo);
        assertThat(errorResponse.getMensagem()).isEqualTo(mensagem);
        assertThat(errorResponse.getStatus()).isEqualTo(status);
    }

    @Test
    public void testCustomErrorResponseWithoutCampo() {
        // Arrange
        LocalDateTime timestamp = LocalDateTime.now();
        String mensagem = "mensagem";
        int status = 400;

        // Act
        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, mensagem, status);

        // Assert
        assertThat(errorResponse.getTimestamp()).isEqualTo(timestamp);
        assertThat(errorResponse.getCampo()).isNull();
        assertThat(errorResponse.getMensagem()).isEqualTo(mensagem);
        assertThat(errorResponse.getStatus()).isEqualTo(status);
    }
}
