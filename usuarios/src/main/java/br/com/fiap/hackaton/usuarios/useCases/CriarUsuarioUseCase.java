package br.com.fiap.hackaton.usuarios.useCases;

import br.com.fiap.hackaton.usuarios.adapters.UsuarioAdapter;
import br.com.fiap.hackaton.usuarios.records.DadosCriacaoUsuarioDTO;
import br.com.fiap.hackaton.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe para implementar a logica de criar um usuário no sistema.
 */
@Service
public class CriarUsuarioUseCase {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioAdapter usuarioAdapter;

    /**
     * Método para criar um usuário no sistema.
     *
     * @param dadosCriacaoUsuarioDTO Objeto com os dados para criação da entidade.
     */
    public void criarUsuario(DadosCriacaoUsuarioDTO dadosCriacaoUsuarioDTO) {

        usuarioRepository.save(usuarioAdapter.converterParaEntity(dadosCriacaoUsuarioDTO));

    }

}
