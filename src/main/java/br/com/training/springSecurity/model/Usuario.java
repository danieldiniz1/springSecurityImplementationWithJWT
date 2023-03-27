package br.com.training.springSecurity.model;

import br.com.training.springSecurity.model.form.TokenForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login,senha,nome;

    public static Usuario valueOf(TokenForm tokenForm){
        Usuario usuario = new Usuario();
        usuario.setLogin(tokenForm.getLogin());
        usuario.setSenha(tokenForm.getKey());
        usuario.setNome(tokenForm.getUser());
        return usuario;
    }
}
