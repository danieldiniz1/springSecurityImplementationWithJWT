package br.com.training.springSecurity.model.form;

import lombok.Data;

@Data
public class TokenForm {

    private String login,key,user;
}
