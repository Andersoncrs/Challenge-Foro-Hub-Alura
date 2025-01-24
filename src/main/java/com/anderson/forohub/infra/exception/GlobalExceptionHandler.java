package com.anderson.forohub.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErroraValidacion>> erroresDeValidacion(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(e.getFieldErrors().stream()
                .map(DatosErroraValidacion::new).toList());
    }

    @ExceptionHandler(CursoNoEncontradoException.class)
    public ResponseEntity<MostrarError> cursoNoEncontrado(CursoNoEncontradoException e){
        return ResponseEntity.badRequest().body(new MostrarError(e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MostrarError> jsonInvalido(HttpMessageNotReadableException e){
        return ResponseEntity.badRequest().body(new MostrarError("El JSON enviado no es valido"));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> elementoNoEncontrado(EntityNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MostrarError> cursoExistente(DataIntegrityViolationException e){
        return ResponseEntity.badRequest().body(new MostrarError("El Curso Ingresado ya Existe" ));
    }

    public record DatosErroraValidacion(String campo, String error){
        public DatosErroraValidacion(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    public record MostrarError(String error){
    }
}

