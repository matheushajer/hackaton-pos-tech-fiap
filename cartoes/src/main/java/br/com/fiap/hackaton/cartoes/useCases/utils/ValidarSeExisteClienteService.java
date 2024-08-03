package br.com.fiap.hackaton.cartoes.useCases.utils;

import br.com.fiap.hackaton.cartoes.http.ClienteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsavel por validar se um cliente já foi criado dentro do microservice de Clientes.
 * Validação é usada para permitir ou não a criação de um cartão, dado cpf informado na chamada.
 */
@Service
public class ValidarSeExisteClienteService {

    @Autowired
    ClienteClient client;

    /**
     * Método consulta o microservice de clientes em busca de um cpf.
     *
     * @param cpf CPF do cliente que está será validado existencia.
     * @throws IllegalArgumentException Exception lançada quando o cliente informado ainda não foi cadastrado.
     */
    public void existeClienteParaCpf(String cpf) {

        if (!client.existeCliente(cpf)) {
            throw new IllegalArgumentException("CPF não pertence a nenhum cliente cadastrado, é preciso criar um" +
                    " cliente com o CPF informado.");
        }

    }

}
