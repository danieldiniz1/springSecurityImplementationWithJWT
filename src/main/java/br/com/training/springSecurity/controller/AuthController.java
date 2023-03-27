package br.com.training.springSecurity.controller;

import br.com.training.springSecurity.config.security.AuthToken;
import br.com.training.springSecurity.config.security.TokenUtil;
import br.com.training.springSecurity.exception.AuthorizationException;
import br.com.training.springSecurity.model.Usuario;
import br.com.training.springSecurity.model.dto.TokenDTO;
import br.com.training.springSecurity.model.form.TokenForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/free")
    public ResponseEntity sayFreeHello(){
        return ResponseEntity.status(200).body("Endpoint que não precisa de autenticação");
    }


    @GetMapping("/block")
    public ResponseEntity sayAuthHello(){
        return ResponseEntity.status(200).body("Endpoint precisa de autenticação");
    }

    @PostMapping("/login")
    public ResponseEntity getTokenDto(@RequestBody TokenForm form){
        if (form.getUser().equals("user") && form.getKey().equals("key"))
            return ResponseEntity.status(200).body(TokenUtil.encodeToken(Usuario.valueOf(form)));
        else
            throw new AuthorizationException("user or key invalid");
    }
}
