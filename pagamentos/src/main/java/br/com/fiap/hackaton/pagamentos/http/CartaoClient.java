package br.com.fiap.hackaton.pagamentos.http;

import br.com.fiap.hackaton.pagamentos.records.DadosCriacaoPagamentoDTO;
import br.com.fiap.hackaton.pagamentos.security.feignConfigSecurity.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Interface para efetuar a comunicação entre os microservices de cartoes e pagamentos.
 */
@FeignClient(value = "cartoes", configuration = FeignClientInterceptor.class)
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/cartoes/efetuar-compra")
    boolean efetuarCompra(@RequestBody DadosCriacaoPagamentoDTO dadosCriacaoPagamentoDTO);
}
