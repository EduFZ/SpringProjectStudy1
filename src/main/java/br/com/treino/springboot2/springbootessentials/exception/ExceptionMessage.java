package br.com.treino.springboot2.springbootessentials.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionMessage extends Exception{
    public ExceptionMessage(String message){super(message);}
}
