package com.proteccion.gestor_tareas_colaborativo.application.service;

import com.proteccion.gestor_tareas_colaborativo.domain.model.Tarea;

import java.util.List;

public interface CrearTareaUseCase {
    Tarea crearTarea(Tarea tarea);
    List<Tarea> findByUsuarioAsignado(Long usuarioId);
    Tarea modificarTarea(Long id, Tarea tareaModificada);
    

}
