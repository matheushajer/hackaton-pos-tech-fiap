package br.com.fiap.hackaton.cartoes.security.feignConfigSecurity;

import br.com.fiap.hackaton.cartoes.security.TokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientInterceptor {

    @Autowired
    private TokenService tokenService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                String token = recuperarTokenDaRequisicao();

                if (token == null) {
                    token = tokenService.gerarToken();
                }

                requestTemplate.header("Authorization", "Bearer " + token);
            }

            private String recuperarTokenDaRequisicao() {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    String cabecalhoDeAutorizacao = attributes.getRequest().getHeader("Authorization");
                    if (cabecalhoDeAutorizacao != null) {
                        return cabecalhoDeAutorizacao.replace("Bearer ", "");
                    }
                }
                return null;
            }
        };
    }

}
