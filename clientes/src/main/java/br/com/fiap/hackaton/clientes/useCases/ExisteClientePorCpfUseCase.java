package br.com.fiap.hackaton.clientes.useCases;

import br.com.fiap.hackaton.clientes.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe para representar o caso de uso de validar se um cliente existe pelo CPF.
 */
@Service
@Transactional
public class ExisteClientePorCpfUseCase {

    @Autowired
    ClienteRepository clienteRepository;

    public boolean existeClieteByCpf(String cpf) {

        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("O CPF informado está vazio ou nulo.");
        }

        return clienteRepository.existsByCpf(cpf);

    }
}
