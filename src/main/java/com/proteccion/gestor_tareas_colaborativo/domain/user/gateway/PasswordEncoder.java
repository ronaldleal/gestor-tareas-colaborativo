package com.proteccion.gestor_tareas_colaborativo.domain.user.gateway;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String password, String encodedPassword);
}
