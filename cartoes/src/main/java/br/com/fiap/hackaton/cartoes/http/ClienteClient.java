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

    @RequestMapping(method = RequestMethod.GET, value = "/clientes/existe-cliente/{cpf}")
    boolean existeCliente(@PathVariable String cpf);

}
