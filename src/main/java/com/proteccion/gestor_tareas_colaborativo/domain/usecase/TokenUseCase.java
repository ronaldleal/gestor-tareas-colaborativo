package com.proteccion.gestor_tareas_colaborativo.domain.usecase;

import com.proteccion.gestor_tareas_colaborativo.application.port.out.PasswordEncoder;
import com.proteccion.gestor_tareas_colaborativo.domain.user.TokenResponse;
import com.proteccion.gestor_tareas_colaborativo.domain.user.exception.UserInternalException;
import com.proteccion.gestor_tareas_colaborativo.domain.user.gateway.JwtProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenUseCase {
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse generateTokenOnly(String userName) {
        try {
            return jwtProvider.generateToken(userName);
        } catch (Exception e) {
            throw new UserInternalException("An issue occurred while generating the authentication token.");
        }
    }
}
