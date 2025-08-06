package com.proteccion.gestor_tareas_colaborativo.web.dto;

import com.proteccion.gestor_tareas_colaborativo.domain.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {
    private String userName;
    private String password;
    private Rol role;
}
