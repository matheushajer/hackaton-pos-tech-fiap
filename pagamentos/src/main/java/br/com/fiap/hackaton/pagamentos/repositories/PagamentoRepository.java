package br.com.fiap.hackaton.pagamentos.repositories;

import br.com.fiap.hackaton.pagamentos.entities.PagamentoEntitty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntitty, Long> {

    Page<PagamentoEntitty> findByCpf(String cpf, Pageable pageable);

}
