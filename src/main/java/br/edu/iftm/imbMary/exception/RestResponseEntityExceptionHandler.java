package br.edu.iftm.imbMary.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        System.out.println("\n\n------------------------------------ aqui");
        return new ResponseEntity<Object>("Access NEGADO message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

}