package br.com.fiap.hackaton.cartoes.exceptions;

/**
 * Classe exception personalidade para tratamento de erros de transação nos cartões.
 * É implementada de fato na classe CustomExceptionHandler.
 */
public class PaymentRequiredException extends RuntimeException {

    public PaymentRequiredException(String message) {
        super(message);
    }

}
