package br.com.training.springSecurity.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FilterSecurity extends OncePerRequestFilter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("Requisição passou pelo filtro de segurança");
        filterChain.doFilter(request,response);
    }
}
