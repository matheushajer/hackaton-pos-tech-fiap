package br.com.fiap.hackaton.pagamentos.exceptions;

public class ServiceCartoesException extends RuntimeException {

    private int status;

    public ServiceCartoesException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
