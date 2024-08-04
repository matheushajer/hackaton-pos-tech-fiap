package br.com.fiap.hackaton.usuarios.repositories;

import br.com.fiap.hackaton.usuarios.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Classe repository para manipulação de dados no banco pela JPA.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UserDetails findByLogin(String login);

}
