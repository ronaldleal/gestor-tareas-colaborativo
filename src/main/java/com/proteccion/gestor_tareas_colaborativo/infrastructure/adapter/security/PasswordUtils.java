package com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.security;


import com.proteccion.gestor_tareas_colaborativo.application.port.out.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordUtils implements PasswordEncoder {
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }
}
