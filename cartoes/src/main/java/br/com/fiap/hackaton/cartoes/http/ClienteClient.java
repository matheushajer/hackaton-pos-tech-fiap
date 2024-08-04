package br.com.fiap.hackaton.cartoes.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Interface para efetuar a comunicação entre os microserviços de clientes e cartoes.
 */
@FeignClient("clientes")
public interface ClienteClient {

    /**
     * Método para efetuar uma chamada ao service de cliente.
     *
     * @param cpf CPF do cliente que será validado.
     * @return Retorna true or false caso o cliente exista no sistema.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/clientes/existe-cliente/{cpf}")
    boolean existeCliente(@PathVariable String cpf);

}
