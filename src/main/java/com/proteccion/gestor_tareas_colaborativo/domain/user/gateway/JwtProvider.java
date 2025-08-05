package com.proteccion.gestor_tareas_colaborativo.domain.user.gateway;

import com.proteccion.gestor_tareas_colaborativo.domain.user.TokenResponse;

public interface JwtProvider {
    TokenResponse generateToken(String userName);
}
