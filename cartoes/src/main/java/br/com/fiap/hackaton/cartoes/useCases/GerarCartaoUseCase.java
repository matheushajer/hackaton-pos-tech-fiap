package br.com.fiap.hackaton.cartoes.useCases;

import br.com.fiap.hackaton.cartoes.adapters.CartaoAdapter;
import br.com.fiap.hackaton.cartoes.entities.CartaoEntity;
import br.com.fiap.hackaton.cartoes.records.DadosCriacaoCartaoDTO;
import br.com.fiap.hackaton.cartoes.repositories.CartaoRepository;
import br.com.fiap.hackaton.cartoes.useCases.utils.ValidarSeExisteClienteService;
import br.com.fiap.hackaton.cartoes.useCases.utils.ValidarSePodeCriarCartaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

/**
 * Classe para implementar o caso de uso de gerar cartões.
 */
@Service
@Transactional
public class GerarCartaoUseCase {

    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    CartaoAdapter cartaoAdapter;
    @Autowired
    ValidarSeExisteClienteService validarSeExisteClienteService;
    @Autowired
    ValidarSePodeCriarCartaoService validarSePodeCriarCartaoService;

    /**
     * Método para efetuar a criação de cartões no sistema.
     *
     * @param dadosCriacaoCartaoDTO Objeto DTO com os dados para criação do cartão.
     */
    public void gerarCartao(DadosCriacaoCartaoDTO dadosCriacaoCartaoDTO) throws AccessDeniedException {

        validarSeExisteClienteService.existeClienteParaCpf(dadosCriacaoCartaoDTO.cpf());

        validarSePodeCriarCartaoService.validarSePodeCriarCartao(dadosCriacaoCartaoDTO.cpf(),
                dadosCriacaoCartaoDTO.limite());

        CartaoEntity cartaoEntity = cartaoAdapter.converterParaEntity(dadosCriacaoCartaoDTO);

        cartaoRepository.save(cartaoEntity);

    }

}
