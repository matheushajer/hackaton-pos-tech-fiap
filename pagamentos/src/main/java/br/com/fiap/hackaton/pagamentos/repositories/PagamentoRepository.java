package br.com.fiap.hackaton.pagamentos.repositories;

import br.com.fiap.hackaton.pagamentos.entities.PagamentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

    Page<PagamentoEntity> findByCpf(String cpf, Pageable pageable);

}
