package br.com.fiap.hackaton.cartoes.exceptions;

public class PaymentRequiredException extends RuntimeException {

    public PaymentRequiredException(String message) {
        super(message);
    }

}
