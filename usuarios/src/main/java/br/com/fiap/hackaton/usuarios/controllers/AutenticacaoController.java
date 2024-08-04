package br.com.fiap.hackaton.usuarios.controllers;

import br.com.fiap.hackaton.usuarios.entities.UsuarioEntity;
import br.com.fiap.hackaton.usuarios.records.DadosTokenJwtDTO;
import br.com.fiap.hackaton.usuarios.records.DadosUsuarioDTO;
import br.com.fiap.hackaton.usuarios.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager manager;
    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJwtDTO> efetuarLogin(@RequestBody @Validated DadosUsuarioDTO dadosUsuarioDTO) {

        var tokenDeAutenticacao = new UsernamePasswordAuthenticationToken(dadosUsuarioDTO.login(), dadosUsuarioDTO.senha());
        var autentication = manager.authenticate(tokenDeAutenticacao);

        var tokenJwt = tokenService.gerarToken((UsuarioEntity) autentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJwtDTO(tokenJwt));

    }

}
