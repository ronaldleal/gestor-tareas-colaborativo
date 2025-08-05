package com.proteccion.gestor_tareas_colaborativo.application.port.out;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String password, String encodedPassword);
}
