package br.com.treino.springboot2.springbootessentials.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonInclude
public class ErrorMessage {

    private String message;
    public ErrorMessage(String message){

        this.message = message;
    }
}
