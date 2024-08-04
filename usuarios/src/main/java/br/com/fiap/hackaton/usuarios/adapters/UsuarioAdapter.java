package br.com.fiap.hackaton.usuarios.adapters;

import br.com.fiap.hackaton.usuarios.entities.UsuarioEntity;
import br.com.fiap.hackaton.usuarios.records.DadosUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Classe para tratamento dos dados vindo pelas APIs, efetuando a criação de um UsuarioEntity, ou dos DTOs nenecessarios
 * e tratados.
 */
@Service
public class UsuarioAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Método para efetuar a conversão dos dados vindos pela API e criar um objeto UsuarioEntity.
     *
     * @param dadosUsuarioDTO Objeto com os dados para criação da entidade.
     * @return UsuarioEntity Objeto com o tratamento adequado dos dados recebidos.
     */
    public UsuarioEntity converterParaEntity(DadosUsuarioDTO dadosUsuarioDTO) {

        String senhaCodificada = passwordEncoder.encode(dadosUsuarioDTO.senha());

        return new UsuarioEntity(
                dadosUsuarioDTO.login(),
                senhaCodificada
        );

    }

}
