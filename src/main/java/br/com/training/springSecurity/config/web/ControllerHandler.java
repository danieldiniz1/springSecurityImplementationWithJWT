package br.com.training.springSecurity.config.web;

import br.com.training.springSecurity.exception.AuthorizationException;
import br.com.training.springSecurity.model.ErroDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErroDTO> authorizationError(AuthorizationException ex, HttpServletRequest http){
        return ResponseEntity.status(401).body(ErroDTO.valueOf(401,ex.getMessage()));
    }
}
