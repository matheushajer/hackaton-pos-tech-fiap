package br.com.fiap.hackaton.usuarios.adapters;

import br.com.fiap.hackaton.usuarios.entities.UsuarioEntity;
import br.com.fiap.hackaton.usuarios.records.DadosCriacaoUsuarioDTO;
import org.springframework.stereotype.Service;

/**
 * Classe para tratamento dos dados vindo pelas APIs, efetuando a criação de um UsuarioEntity, ou dos DTOs nenecessarios
 * e tratados.
 */
@Service
public class UsuarioAdapter {

    /**
     * Método para efetuar a conversão dos dados vindos pela API e criar um objeto UsuarioEntity.
     *
     * @param dadosCriacaoUsuarioDTO Objeto com os dados para criação da entidade.
     * @return UsuarioEntity Objeto com o tratamento adequado dos dados recebidos.
     */
    public UsuarioEntity converterParaEntity(DadosCriacaoUsuarioDTO dadosCriacaoUsuarioDTO) {

        return new UsuarioEntity(
                dadosCriacaoUsuarioDTO.login(),
                dadosCriacaoUsuarioDTO.senha()
        );

    }

}
