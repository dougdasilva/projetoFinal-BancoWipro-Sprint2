package com.wipro.projetofinal.controller.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wipro.projetofinal.service.exeption.AlreadyExistAccountByCpf;
import com.wipro.projetofinal.service.exeption.InvalidValueException;
import com.wipro.projetofinal.service.exeption.ResourceNotFoundExcception;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundExcception.class)
    public ResponseEntity<StandardError> resourceNorFound(ResourceNotFoundExcception e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND ;
        StandardError err = new StandardError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    

    @ExceptionHandler(AlreadyExistAccountByCpf.class)
    public ResponseEntity<StandardError> alreadyExistAccountByCpf(AlreadyExistAccountByCpf e, HttpServletRequest request){
        String error = "Conta já existente";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE ;
        StandardError err = new StandardError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> alreadyExistAccountByCpf(NullPointerException e, HttpServletRequest request){
        String error = "Valor inexistente";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
    
  @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<StandardError> numberException(InvalidValueException e, HttpServletRequest request){
    	   String error = "Número inválido";
           HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
           StandardError err = new StandardError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
           return ResponseEntity.status(status).body(err);
    }
}
