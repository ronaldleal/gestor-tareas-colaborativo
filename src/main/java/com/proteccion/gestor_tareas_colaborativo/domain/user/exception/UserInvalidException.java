package com.proteccion.gestor_tareas_colaborativo.domain.user.exception;

public class UserInvalidException extends RuntimeException {
    public UserInvalidException(String message) {
        super(message);
    }
}
