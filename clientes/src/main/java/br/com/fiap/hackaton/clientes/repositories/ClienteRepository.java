package br.com.fiap.hackaton.clientes.repositories;

import br.com.fiap.hackaton.clientes.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    boolean existsByCpf(String cpf);

}
