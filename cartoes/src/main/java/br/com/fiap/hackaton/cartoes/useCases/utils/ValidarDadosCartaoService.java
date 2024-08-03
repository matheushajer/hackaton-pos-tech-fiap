package br.com.fiap.hackaton.cartoes.useCases.utils;

import br.com.fiap.hackaton.cartoes.entities.CartaoEntity;
import br.com.fiap.hackaton.cartoes.records.DadosEfetuarCompraDTO;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * Classe para efetuar a validação se os dados do cartão fornecidos pela API, estão corretos.
 */
@Service
public class ValidarDadosCartaoService {

    /**
     * Método para validar se, da lista de cartões do cliente, algum dos cartões é o mesmo fornecido para pagamento.
     *
     * @param cartaoEntity          Objeto com os cartões do cliente, carregados do banco.
     * @param dadosEfetuarCompraDTO Objeto com os dados de pagamento fornecidos pela API.
     * @throws AccessDeniedException Exception retornada caso os dados fornecidos, não batam com os dados carregados do banco.
     */
    public Long validarDadosDoCartao(List<CartaoEntity> cartaoEntity, DadosEfetuarCompraDTO dadosEfetuarCompraDTO)
            throws AccessDeniedException {

        for (CartaoEntity cartao : cartaoEntity) {
            if (cartao.getNumero().equals(dadosEfetuarCompraDTO.numero()) &&
                    cartao.getDataValidade().equals(dadosEfetuarCompraDTO.data_validade()) &&
                    cartao.getCvv().equals(dadosEfetuarCompraDTO.cvv())) {
                return cartao.getId();
            }
        }

        throw new AccessDeniedException("Os dados de cartão fornecidos para pagamento estão incorretos.");

    }

}
