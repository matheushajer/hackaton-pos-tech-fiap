package br.com.fiap.hackaton.clientes.adapters;

import br.com.fiap.hackaton.clientes.entities.ClienteEntity;
import br.com.fiap.hackaton.clientes.records.DadosCriacaoClienteDTO;
import org.springframework.stereotype.Service;

/**
 * Classe para efetuar o tratamento dos dados vindo das APIs
 * e dos dados retornados pela aplicação.
 */
@Service
public class ClienteAdapter {

    /**
     * Método para converter os dados vindo pela API em um ClienteEntity.
     *
     * @param dadosCriacaoClienteDTO DTO com os dados recebidos pela API.
     * @return CliienteEntity retorna uma nova entidade cliente, com os dados tratados.
     */
    public ClienteEntity converterParaEntity(DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        return new ClienteEntity(
                dadosCriacaoClienteDTO.cpf(),
                dadosCriacaoClienteDTO.nome(),
                dadosCriacaoClienteDTO.email(),
                dadosCriacaoClienteDTO.telefone(),
                dadosCriacaoClienteDTO.rua(),
                dadosCriacaoClienteDTO.cidade(),
                dadosCriacaoClienteDTO.estado(),
                dadosCriacaoClienteDTO.cep(),
                dadosCriacaoClienteDTO.pais()
        );

    }

}
