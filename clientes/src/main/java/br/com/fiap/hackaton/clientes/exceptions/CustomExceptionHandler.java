package br.com.fiap.hackaton.clientes.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Após envio de dados inválidos pelos DTOs, durante uma chamda de API,
 * essa será a classe responsável pelo retorno tratado do erro em questão.
 * Classe tratará apenas dos erros de Validação dos DTOs.
 */
@RestControllerAdvice
public class CustomExceptionHandler {

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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalArgumentExceptions(IllegalArgumentException ex) {

        String mensagem = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.BAD_REQUEST.value();

        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, mensagem, status);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleEntityNotFoundExceptions(EntityNotFoundException ex) {

        String mensagem = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, mensagem, status);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
