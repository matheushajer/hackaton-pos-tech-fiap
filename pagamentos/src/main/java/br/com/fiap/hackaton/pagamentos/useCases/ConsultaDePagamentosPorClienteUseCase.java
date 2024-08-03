package br.com.fiap.hackaton.pagamentos.useCases;

import br.com.fiap.hackaton.pagamentos.adapters.PagamentoAdapter;
import br.com.fiap.hackaton.pagamentos.records.DadosConsultaPagamentoPorCpf;
import br.com.fiap.hackaton.pagamentos.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Classe para implementação do caso de uso de consultar todos os pagamentos de um determinado cliente.
 */
@Service
public class ConsultaDePagamentosPorClienteUseCase {

    @Autowired
    PagamentoRepository pagamentoRepository;
    @Autowired
    PagamentoAdapter pagamentoAdapter;

    /**
     * Método para converter uma lista de PagamentosEntity para um Page de DadosConsultaPagamentoPorCpf.
     *
     * @param cpf      CPF usado para busca dos registros.
     * @param pageable Objeto para parametros de paginação.
     * @return Retorna um Page de DadosConsultaPagamentoPorCpf.
     */
    public Page<DadosConsultaPagamentoPorCpf> consultarPagamentosPorCliente(String cpf, Pageable pageable) {

        if (cpf.isBlank()) {
            throw new IllegalArgumentException("CPF do cliente não informado para busca");
        }

        return pagamentoRepository.findByCpf(cpf, pageable)
                .map(pagamentoAdapter::converterParaDadosConsultaPorCpf);
    }

}

