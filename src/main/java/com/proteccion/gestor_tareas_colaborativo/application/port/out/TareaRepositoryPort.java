package com.proteccion.gestor_tareas_colaborativo.application.port.out;

import com.proteccion.gestor_tareas_colaborativo.domain.model.Tarea;

import java.util.List;

public interface TareaRepositoryPort {
    Tarea save(Tarea tarea);
    List<Tarea> findByUsuarioAsignado(Long usuarioId);
}
