package br.com.fiap.hackaton.clientes.useCases;

import br.com.fiap.hackaton.clientes.adapters.ClienteAdapter;
import br.com.fiap.hackaton.clientes.entities.ClienteEntity;
import br.com.fiap.hackaton.clientes.records.DadosCriacaoClienteDTO;
import br.com.fiap.hackaton.clientes.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe para representar o caso de uso do registro de um cliente.
 */
@Service
@Transactional
public class RegistrarClienteUseCase {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteAdapter clienteAdapter;

    /**
     * Método para efetuar a criação/registro de uma entidade ClienteEntity e gravar no banco.
     *
     * @param dadosCriacaoClienteDTO Dados recebidos pela API para criação da entidade cliente.
     */
    public String registrarCliente(DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        if (clienteRepository.existsByCpf(dadosCriacaoClienteDTO.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado! Não é possivel informar o mesmo CPF para dois clientes");
        }

        ClienteEntity clienteEntity = clienteAdapter.converterParaEntity(dadosCriacaoClienteDTO);

        clienteRepository.save(clienteEntity);

        return "id_cliente: " + clienteEntity.getId();

    }


}
