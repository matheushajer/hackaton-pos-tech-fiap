package br.com.fiap.hackaton.cartoes.adapters;

import br.com.fiap.hackaton.cartoes.entities.CartaoEntity;
import br.com.fiap.hackaton.cartoes.records.DadosCriacaoCartaoDTO;
import org.springframework.stereotype.Service;

/**
 * Classe adapter para tratamento de dados recebidos ou enviados para APIs, relacionados
 * ao objeto CartaoEntity e DTOs.
 */
@Service
public class CartaoAdapter {

    /**
     * Método para converter os dados vindos pela API, para uma entidade no sistema.
     *
     * @param dadosCriacaoCartaoDTO Objeto DTO com os dados para criação da entidade.
     * @return CartaoEntity entidade criada após conversão dos dados.
     */
    public CartaoEntity converterParaEntity(DadosCriacaoCartaoDTO dadosCriacaoCartaoDTO) {

        return new CartaoEntity(
                dadosCriacaoCartaoDTO.cpf(),
                dadosCriacaoCartaoDTO.limite(),
                dadosCriacaoCartaoDTO.numero(),
                dadosCriacaoCartaoDTO.data_validade(),
                dadosCriacaoCartaoDTO.cvv()
        );

    }

}
