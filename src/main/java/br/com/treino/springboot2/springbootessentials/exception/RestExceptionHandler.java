package br.com.treino.springboot2.springbootessentials.exception;



import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler  {
    @ExceptionHandler(EduardoException.class)
    public ResponseEntity<ErrorMessage> handlerEduardoException(EduardoException ex){
         ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
         return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NaiaraException.class)
    public ResponseEntity<ErrorMessage> handlerNaiaraException(NaiaraException ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return  new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }

}

