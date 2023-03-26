package br.com.training.springSecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErroDTO {
    private Integer status;
    private String message;
//    private LocalDateTime timeStamp;

    public static ErroDTO valueOf(Integer status,String message){
        return new ErroDTO(status,message);
    }
}
