package br.com.training.springSecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
