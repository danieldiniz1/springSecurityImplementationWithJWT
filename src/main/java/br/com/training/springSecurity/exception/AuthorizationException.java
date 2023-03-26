package br.com.training.springSecurity.exception;

public class AuthorizationException extends RuntimeException{

    public AuthorizationException(String message) {
        super(message);
    }
}
