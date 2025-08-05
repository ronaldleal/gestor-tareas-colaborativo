package com.proteccion.gestor_tareas_colaborativo.application.service;

import com.proteccion.gestor_tareas_colaborativo.application.port.out.TareaRepositoryPort;
import com.proteccion.gestor_tareas_colaborativo.domain.model.Tarea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrearTareaService implements CrearTareaUseCase{
    private final TareaRepositoryPort tareaRepository;

    @Override
    public Tarea crearTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public List<Tarea> findByUsuarioAsignado(Long usuarioId) {
        Tarea tarea = new Tarea();
        tarea.setUsuarioAsignadoId(usuarioId);
        return tareaRepository.findByUsuarioAsignado(tarea);
    }

}
