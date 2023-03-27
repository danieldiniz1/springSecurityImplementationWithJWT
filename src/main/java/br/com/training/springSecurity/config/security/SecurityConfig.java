package br.com.training.springSecurity.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()                                                   //desabilitar autenticacao csrf pq a tratativa sera implementada
                .authorizeHttpRequests()                                                //requisicoes passiveis de autorizacao
                .requestMatchers(HttpMethod.GET,"*/free").permitAll()          //requuisicoes liberadas para o especificado
                .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                .anyRequest().authenticated().and().cors();                             //outras requisições precisam de autenticação

        httpSecurity.addFilterBefore(new FilterSecurity(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
