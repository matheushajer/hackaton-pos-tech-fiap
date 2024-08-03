package br.com.fiap.hackaton.cartoes.useCases.utils;

import br.com.fiap.hackaton.cartoes.repositories.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;

/**
 * Classe responsvel pela validação da regra de negocio que, um cliente pode ter apenas dois cartões em vinculados.
 */
@Service
public class ValidarSePodeCriarCartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    /**
     * Método valida se o cliente já possui dois cartões vinculados ao seu registro.
     *
     * @param cpf CPF do cliente que terá a validação efetuada.
     * @throws AccessDeniedException    Exception lançada quando uma tentativa de criar um terceiro cartão para um cliente
     *                                  é efetuada.
     * @throws IllegalArgumentException Expection lançada quando o limite para criação do cartão, é negativo.
     */
    public void validarSePodeCriarCartao(String cpf, BigDecimal limite) throws AccessDeniedException {

        if (limite.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O limite do cartão não pode ser criado com valor negativo");
        }

        if (cartaoRepository.countByCpf(cpf) >= 2) {
            throw new AccessDeniedException("CPF informado já possui 2 cartões cadastrados");
        }

    }

}
