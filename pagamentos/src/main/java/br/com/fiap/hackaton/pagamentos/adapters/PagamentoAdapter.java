package br.com.fiap.hackaton.pagamentos.adapters;

import br.com.fiap.hackaton.pagamentos.entities.PagamentoEntity;
import br.com.fiap.hackaton.pagamentos.records.DadosConsultaPagamentoPorCpf;
import br.com.fiap.hackaton.pagamentos.records.DadosCriacaoPagamentoDTO;
import org.springframework.stereotype.Service;

/**
 * Classe adapter para efetuar o tratamento de dados vindo pelas APIs do serviço de pagamentos.
 */
@Service
public class PagamentoAdapter {

    /**
     * Método para efetuar a conversão dos dados recebidos  pela API, para um objeto do tipo PagamentoEntity.
     *
     * @param dadosCriacaoPagamentoDTO Objeto com os dados a serem convertidos.
     * @return PagamentoEntitty Objeto com os dados convertidos e tratados.
     */
    public PagamentoEntity converterParaEntity(DadosCriacaoPagamentoDTO dadosCriacaoPagamentoDTO) {

        return new PagamentoEntity(
                dadosCriacaoPagamentoDTO.cpf(),
                dadosCriacaoPagamentoDTO.numero(),
                dadosCriacaoPagamentoDTO.data_validade(),
                dadosCriacaoPagamentoDTO.cvv(),
                dadosCriacaoPagamentoDTO.valor()
        );

    }

    /**
     * Método para converter uma entidade pagamento para um objeto DadosConsultaPagamentoPorCpf, que será
     * retornado pela API de consulta.
     *
     * @param entitty Lista com todos os registros de pagamento efetuados por um determinado CPF.
     * @return Retorna uma lista de DadosConsultaPagamentoPorCpf, com os dados tratados para retorno.
     */

    public DadosConsultaPagamentoPorCpf converterParaDadosConsultaPorCpf(PagamentoEntity entitty) {
        return new DadosConsultaPagamentoPorCpf(
                entitty.getValorCompra(),
                entitty.getDescricao(),
                entitty.getMetodoPagamento(),
                entitty.getStatusPagamento()
        );
    }

}
