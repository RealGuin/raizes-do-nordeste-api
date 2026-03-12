package com.raizesdonordeste.raizesnovoapi.api.exception;

import jakarta.servlet.http.HttpServletRequest;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.raizesdonordeste.raizesnovoapi.domain.exception.ConflitoException;
import com.raizesdonordeste.raizesnovoapi.domain.exception.RecursoNaoEncontradoException;
import com.raizesdonordeste.raizesnovoapi.domain.exception.NaoAutorizadoException;
import com.raizesdonordeste.raizesnovoapi.domain.exception.ValidacaoException;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            RecursoNaoEncontradoException ex,
            HttpServletRequest request) {

        ErrorResponse problem = new ErrorResponse();

        problem.setType("https://api.raizesdonordeste.com/errors/not-found");
        problem.setTitle("Recurso não encontrado");
        problem.setStatus(HttpStatus.NOT_FOUND.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }
    
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            ValidacaoException ex,
            HttpServletRequest request) {

        ErrorResponse problem = new ErrorResponse();

        problem.setType("https://api.raizesdonordeste.com/errors/validation-error");
        problem.setTitle("Erro de validação");
        problem.setStatus(HttpStatus.UNPROCESSABLE_CONTENT.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(problem);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Erro de validação");

        ErrorResponse problem = new ErrorResponse();

        problem.setType("https://api.raizesdonordeste.com/errors/validation-error");
        problem.setTitle("Erro de validação");
        problem.setStatus(HttpStatus.UNPROCESSABLE_CONTENT.value());
        problem.setDetail(mensagem);
        problem.setInstance(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(problem);
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        ErrorResponse problem = new ErrorResponse();

        problem.setType("https://api.raizesdonordeste.com/errors/bad-request");
        problem.setTitle("Requisição inválida");
        problem.setStatus(HttpStatus.BAD_REQUEST.value());
        problem.setDetail("Requisição inválida ou campo com formato incorreto.");
        problem.setInstance(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }
    
    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<ErrorResponse> handleConflict(
            ConflitoException ex,
            HttpServletRequest request) {

        ErrorResponse problem = new ErrorResponse();

        problem.setType("https://api.raizesdonordeste.com/errors/conflict");
        problem.setTitle("Conflito");
        problem.setStatus(HttpStatus.CONFLICT.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(problem);
    }
    
    @ExceptionHandler(NaoAutorizadoException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(
            NaoAutorizadoException ex,
            HttpServletRequest request) {

        ErrorResponse problem = new ErrorResponse();

        problem.setType("https://api.raizesdonordeste.com/errors/unauthorized");
        problem.setTitle("Não autorizado");
        problem.setStatus(HttpStatus.UNAUTHORIZED.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problem);
    }
    
    
    
}
