package br.com.fiap.hackaton.cartoes.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.senha.token}")
    private String senha_tokens;

    public String gerarToken() {

        // Define a expiração para cada 2 minutos
        long tempoParaExpiracao = 2 * 60 * 1000;
        Date dataParaExpiracao = new Date(System.currentTimeMillis() + tempoParaExpiracao);

        try {
            Algorithm algoritomo = Algorithm.HMAC256(senha_tokens);

            return JWT.create()
                    .withIssuer("Hackaton Autentication APIs")
                    .withExpiresAt(dataParaExpiracao)
                    .sign(algoritomo);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }

    }


    public String getSubject(String tokenJwt) {

        try {
            Algorithm algoritomo = Algorithm.HMAC256(senha_tokens);
            return JWT.require(algoritomo)
                    .withIssuer("Hackaton Autentication APIs")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new BadCredentialsException("Token Invalido!");
        }


    }

}
