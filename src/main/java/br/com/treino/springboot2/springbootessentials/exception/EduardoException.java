package br.com.treino.springboot2.springbootessentials.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EduardoException extends Exception{
    public EduardoException(String message){
        super(message);
    }
}
