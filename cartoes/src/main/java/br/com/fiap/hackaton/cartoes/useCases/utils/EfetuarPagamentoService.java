package br.com.fiap.hackaton.cartoes.useCases.utils;

import br.com.fiap.hackaton.cartoes.entities.CartaoEntity;
import br.com.fiap.hackaton.cartoes.exceptions.PaymentRequiredException;
import br.com.fiap.hackaton.cartoes.records.DadosEfetuarCompraDTO;
import br.com.fiap.hackaton.cartoes.repositories.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Classe responsavel pela logica de efetuar um pagamento para um cartão.
 */
@Service
public class EfetuarPagamentoService {

    @Autowired
    CartaoRepository cartaoRepository;

    /**
     * Método para efetuar o pagamento de uma solicitação de compra via API.
     * Caso o valor da compra não seja maior que o limite disponivel, a compra é aprovada.
     *
     * @param id          ID do cartão a ser debitado a compra.
     * @param dadosCompra Objeto com os dados da solicitação de compra.
     */
    public void efetuarPagamento(Long id, DadosEfetuarCompraDTO dadosCompra) {

        CartaoEntity cartaoEntity = cartaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cartão não encontrado"));

        BigDecimal novoLimite = cartaoEntity.getLimite().subtract(dadosCompra.valor());

        if (novoLimite.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentRequiredException("Compra Recusada! O valor da compra é maior que o limite do cartão");
        }

        cartaoEntity.setLimite(novoLimite);
        cartaoRepository.save(cartaoEntity);

    }

}
