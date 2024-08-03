package br.com.fiap.hackaton.cartoes.repositories;

import br.com.fiap.hackaton.cartoes.entities.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {

    @Query("SELECT COUNT(c) FROM CartaoEntity c WHERE c.cpf = :cpf")
    long countByCpf(@Param("cpf") String cpf);

    @Query("SELECT COUNT(c) FROM CartaoEntity c WHERE c.numero = :numero")
    long countByNumero(@Param("numero") String numero);

}
