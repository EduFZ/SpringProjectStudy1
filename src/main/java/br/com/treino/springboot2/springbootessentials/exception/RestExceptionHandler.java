package br.com.treino.springboot2.springbootessentials.exception;



import br.com.treino.springboot2.springbootessentials.validation.RepeatNameValidation;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler  {

    @ExceptionHandler(ExceptionMessage.class)
    public ResponseEntity<ErrorMessage> handlerExceptionMessage(ExceptionMessage ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}

