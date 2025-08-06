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

    @Override
    public List<Tarea> findByUsuarioAsignado(Long usuarioId) {
        Tarea tarea = new Tarea();
        tarea.setUsuarioAsignadoId(usuarioId);
        return tareaRepository.findByUsuarioAsignado(tarea);
    }

    @Override
    public Tarea modificarTarea(Long id, Tarea tareaModificada) {
        Tarea tareaExistente = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        tareaExistente.setTitulo(tareaModificada.getTitulo());
        tareaExistente.setDescripcion(tareaModificada.getDescripcion());
        tareaExistente.setFechaVencimiento(tareaModificada.getFechaVencimiento());
        tareaExistente.setUsuarioAsignadoId(tareaModificada.getUsuarioAsignadoId());
        tareaExistente.setEstado(tareaModificada.getEstado());

        return tareaRepository.save(tareaExistente);
    }

}
