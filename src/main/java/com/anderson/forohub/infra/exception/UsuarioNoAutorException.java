package com.anderson.forohub.infra.exception;

public class UsuarioNoAutorException extends RuntimeException {
    public UsuarioNoAutorException(String message) {
        super(message);
    }
}
