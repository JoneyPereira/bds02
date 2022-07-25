package com.devsuperior.bds02.controllers.exceptions;

import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StanderError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){

        StanderError erro = new StanderError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setError("Recurso não encontrado!");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StanderError> database(DatabaseException e, HttpServletRequest request){

        StanderError erro = new StanderError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(HttpStatus.BAD_REQUEST.value());;
        erro.setError("Recurso não permitido!");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
