package br.com.training.springSecurity.config.security;

import br.com.training.springSecurity.exception.AuthorizationException;
import br.com.training.springSecurity.model.ErroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FilterSecurity extends OncePerRequestFilter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException, RuntimeException {
        LOGGER.info("Requisição passou pelo filtro de segurança");
        if (request.getHeader("Authorization") != null){
            LOGGER.info(request.getHeader("Authorization"));
            Authentication authentication = TokenUtil.decodeToken(request);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication); // se autenticação funcionar, passa ela para a frente liberando o filtro
            } else {
                response.setStatus(401);
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().print(mapper.writeValueAsString(ErroDTO.valueOf(401, "user unauthorized")));
                response.getWriter().flush();
                response.getWriter().flush();
                return;
            }

        }
        filterChain.doFilter(request,response);
    }
}
