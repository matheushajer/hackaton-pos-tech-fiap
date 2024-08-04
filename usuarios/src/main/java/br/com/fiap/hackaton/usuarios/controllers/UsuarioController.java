package br.com.fiap.hackaton.usuarios.controllers;

import br.com.fiap.hackaton.usuarios.records.DadosCriacaoUsuarioDTO;
import br.com.fiap.hackaton.usuarios.useCases.CriarUsuarioUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe para gerenciamento dos endpoints do serviço de usuário.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    CriarUsuarioUseCase criarUsuarioUseCase;

    /**
     * Endpoint responsavel por criar um usuário no sistema.
     *
     * @param dadosCriacaoUsuarioDTO
     */
    @PostMapping("/criar-usuario")
    public void criarUsuario(@RequestBody @Validated DadosCriacaoUsuarioDTO dadosCriacaoUsuarioDTO) {
        criarUsuarioUseCase.criarUsuario(dadosCriacaoUsuarioDTO);
    }


}
