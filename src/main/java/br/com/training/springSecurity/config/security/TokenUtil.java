package br.com.training.springSecurity.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

public class TokenUtil {

    public static Authentication decodeToken(HttpServletRequest request) {
        if (request.getHeader("Authorization").equals("Bearer eyJhbGciOiJIUzI1NiJ9"))
            return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
        return null;
    }
}
