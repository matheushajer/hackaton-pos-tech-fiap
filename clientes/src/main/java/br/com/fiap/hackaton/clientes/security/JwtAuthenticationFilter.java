package br.com.fiap.hackaton.clientes.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);

        if (token != null) {

            String subject = tokenService.getSubject(token);

            Authentication authentication = new UsernamePasswordAuthenticationToken(subject, null, Collections.emptyList());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    }


    /**
     * Método auxiliar para recuperação do token JWT do cabeçalho da requisição.
     *
     * @param request Objeto com as informaçõesdo cabeçalho.
     * @return String com o token informado.
     */
    private String recuperarToken(HttpServletRequest request) {

        var cabecalhoDeAutorizacao = request.getHeader("Authorization");

        if (cabecalhoDeAutorizacao != null) {
            return cabecalhoDeAutorizacao.replace("Bearer ", "");
        }

        return null;

    }

}
