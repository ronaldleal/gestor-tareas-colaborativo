package com.proteccion.gestor_tareas_colaborativo.domain.user;

import com.proteccion.gestor_tareas_colaborativo.domain.model.Rol;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class User {
    private final String userName;
    private final String password;
    private final Rol role;
}
