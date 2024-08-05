package br.com.fiap.hackaton.pagamentos.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(ServiceCartoesException.class)
    public ResponseEntity<CustomErrorResponse> handleCartoesExceptions(ServiceCartoesException ex) {

        String mensagem = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        int status = ex.getStatus();

        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, mensagem, status);

        return ResponseEntity.status(ex.getStatus()).body(errorResponse);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccessDeniedExceptions(AccessDeniedException ex) {

        String mensagem = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();

        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, mensagem);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CustomErrorResponse> handleBadCredentialsExceptions(BadCredentialsException ex) {

        String mensagem = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();
        int status = HttpStatus.UNAUTHORIZED.value();

        CustomErrorResponse errorResponse = new CustomErrorResponse(timestamp, mensagem, status);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

}
