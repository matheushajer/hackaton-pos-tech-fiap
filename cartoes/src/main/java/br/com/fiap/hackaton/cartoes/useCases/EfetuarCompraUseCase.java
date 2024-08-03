package br.com.fiap.hackaton.cartoes.useCases;

import br.com.fiap.hackaton.cartoes.entities.CartaoEntity;
import br.com.fiap.hackaton.cartoes.records.DadosEfetuarCompraDTO;
import br.com.fiap.hackaton.cartoes.repositories.CartaoRepository;
import br.com.fiap.hackaton.cartoes.useCases.utils.EfetuarPagamentoService;
import br.com.fiap.hackaton.cartoes.useCases.utils.ValidarDadosCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * Classe responsavel por validar se o cartão possui limite para efetur uma determinada compra.
 */
@Service
public class EfetuarCompraUseCase {

    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    ValidarDadosCartaoService validarDadosCartao;
    @Autowired
    EfetuarPagamentoService efetuarPagamentoService;

    /**
     * Método para validar e efetuar uma solicitação de compra vindo pela API.
     *
     * @param dadosEfetuarCompraDTO Objeto com os dados da solicitação de comrpa.
     * @throws AccessDeniedException Exeception lançada caso os dados do cartão fornecidos sejam invalidos.
     */
    public boolean efetuarPagamento(DadosEfetuarCompraDTO dadosEfetuarCompraDTO) throws AccessDeniedException {

        if (dadosEfetuarCompraDTO.valor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor da compra não pode ser negativo");
        }

        List<CartaoEntity> cartaoEntity = cartaoRepository.findByCpf(dadosEfetuarCompraDTO.cpf());

        long idCartao = validarDadosCartao.validarDadosDoCartao(cartaoEntity, dadosEfetuarCompraDTO);

        efetuarPagamentoService.efetuarPagamento(idCartao, dadosEfetuarCompraDTO);

        return true;

    }

}
