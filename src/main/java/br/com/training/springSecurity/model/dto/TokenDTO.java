package br.com.training.springSecurity.model.dto;

import lombok.Data;

@Data
public class TokenDTO {

    private String token;
    private String expired;

    public static TokenDTO valueOf(String token){
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        tokenDTO.setExpired("token expired in 5 minutes.");
        return tokenDTO;
    }
}
