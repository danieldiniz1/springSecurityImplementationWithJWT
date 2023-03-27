package br.com.training.springSecurity.config.security;

import br.com.training.springSecurity.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    private static final String EMISSOR = "Sistem32";
    private static final String TOKEN_HEADER = "Bearer ";
    private static final String TOKEN_KEY = "fe0d554a85fe46d9944221d9284e4add";
    private static final Long TOKEN_EXPIRED = 36000l;

    public static AuthToken encodeToken(Usuario usuario){
        Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
        String tokenJWT = Jwts.builder()
                .setSubject(usuario.getLogin())
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRED))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return new AuthToken(TOKEN_HEADER + tokenJWT);
    }

    public static Authentication decodeToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("Authorization");
            jwtToken = jwtToken.replace(TOKEN_HEADER, "");

            //leitura do token(parse)
            Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(TOKEN_KEY.getBytes()).build().parseClaimsJws(jwtToken);

            String usuario = jwsClaims.getBody().getSubject();
            String emissor = jwsClaims.getBody().getIssuer();
            Date expiration = jwsClaims.getBody().getExpiration();
            if (usuario.length() > 0 && emissor.equals(EMISSOR) && expiration.after(new Date(System.currentTimeMillis())))
                return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
