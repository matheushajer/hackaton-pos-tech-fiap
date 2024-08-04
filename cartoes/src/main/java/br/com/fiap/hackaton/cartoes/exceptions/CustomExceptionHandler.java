package br.com.fiap.hackaton.cartoes.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

/**
 * Após envio de dados inválidos pelos DTOs, durante uma chamda de API,
 * essa será a classe responsável pelo retorno tratado do erro em questão.
 * Classe tratará apenas dos erros de Validação dos DTOs.
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * Método para retorno de exception personalidado em caso de MethodArgumentNotValidException.
     *
     * @param ex Objeto exception que será manipulado.
     * @return Retorna um CustomErrorResponse, com os dados tratados do erro.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        String campo = fieldError.getField();
        String mensagem = fieldError.getDefaultMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.BAD_REQUEST.value();

        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, campo, mensagem, status);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Método para retorno de exception personalidado em caso de IllegalArgumentException.
     *
     * @param ex Objeto exception que será manipulado.
     * @return Retorna um CustomErrorResponse, com os dados tratados do erro.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalArgumentExceptions(IllegalArgumentException ex) {

        String mensagem = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.BAD_REQUEST.value();

        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, mensagem, status);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Método para retorno de exception personalidado em caso de EntityNotFoundException.
     *
     * @param ex Objeto exception que será manipulado.
     * @return Retorna um CustomErrorResponse, com os dados tratados do erro.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleEntityNotFoundExceptions(EntityNotFoundException ex) {

        String mensagem = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, mensagem, status);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * Método para retorno de exception personalidado em caso de AccessDeniedException.
     *
     * @param ex Objeto exception que será manipulado.
     * @return Retorna um CustomErrorResponse, com os dados tratados do erro.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccessDeniedExceptions(AccessDeniedException ex) {

        String mensagem = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.FORBIDDEN.value();

        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, mensagem, status);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);

    }

    /**
     * Método para retorno de exception personalidado em caso de PaymentRequiredException.
     *
     * @param ex Objeto exception que será manipulado.
     * @return Retorna um CustomErrorResponse, com os dados tratados do erro.
     */
    @ExceptionHandler(PaymentRequiredException.class)
    public ResponseEntity<CustomErrorResponse> handlePaymentRequiredExceptions(PaymentRequiredException ex) {

        String mensagem = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.PAYMENT_REQUIRED.value();

        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, mensagem, status);

        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(errorResponse);

    }


}
