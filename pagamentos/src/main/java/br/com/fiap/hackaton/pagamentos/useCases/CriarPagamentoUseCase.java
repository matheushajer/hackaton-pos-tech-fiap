package br.com.fiap.hackaton.pagamentos.useCases;

import br.com.fiap.hackaton.pagamentos.adapters.PagamentoAdapter;
import br.com.fiap.hackaton.pagamentos.entities.PagamentoEntitty;
import br.com.fiap.hackaton.pagamentos.entities.enuns.StatusPagamentoEnum;
import br.com.fiap.hackaton.pagamentos.exceptions.ServiceCartoesException;
import br.com.fiap.hackaton.pagamentos.http.CartaoClient;
import br.com.fiap.hackaton.pagamentos.records.DadosCriacaoPagamentoDTO;
import br.com.fiap.hackaton.pagamentos.repositories.PagamentoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Classe para implementar a logica do caso de uso de criar um pagamento.
 */
@Service
public class CriarPagamentoUseCase {

    @Autowired
    PagamentoAdapter pagamentoAdapter;
    @Autowired
    CartaoClient cartaoClient;
    @Autowired
    PagamentoRepository pagamentoRepository;
    @Autowired
    ObjectMapper objectMapper;

    /**
     * Método para criar um registro de pagamento.
     *
     * @param dadosCriacaoPagamentoDTO Objeto com os dados para criação do registro.
     * @return retorna o id do pagamento, este serve como chave_pagamento, pois é o identificador unico de cada pagamento.
     */
    public String criarRegistroDePagamento(DadosCriacaoPagamentoDTO dadosCriacaoPagamentoDTO) {

        PagamentoEntitty pagamentoEntitty = pagamentoAdapter.converterParaEntity(dadosCriacaoPagamentoDTO);

        try {
            if (cartaoClient.efetuarCompra(dadosCriacaoPagamentoDTO)) {
                pagamentoEntitty.setStatusPagamento(StatusPagamentoEnum.APROVADO);
            }
        } catch (FeignException e) {
            String errorMessage = extractErrorMessage(e.contentUTF8());
            throw new ServiceCartoesException(errorMessage, e.status());
        }

        pagamentoRepository.save(pagamentoEntitty);

        return "chave_pagamento: " + pagamentoEntitty.getId();

    }

    /**
     * Método auxiliar, para extração da mensagem de erro, vindo do serviço de cartoes.
     *
     * @param json contentUTF8 vindo da Exception FeignException.
     * @return Retorna apensa a mensagem de erro vinda da exception.
     */
    private String extractErrorMessage(String json) {
        try {
            JsonNode root = objectMapper.readTree(json);
            return root.path("mensagem").asText();
        } catch (IOException e) {
            return "Erro ao processar a mensagem de erro";
        }
    }

}
