package com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.persistence;

import com.proteccion.gestor_tareas_colaborativo.application.port.out.TareaRepositoryPort;
import com.proteccion.gestor_tareas_colaborativo.domain.model.Tarea;
import com.proteccion.gestor_tareas_colaborativo.infrastructure.repository.TareaJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TareaJpaAdapter implements TareaRepositoryPort {


    private final TareaJpaRepository repository;

    @Override
    public Tarea save(Tarea tarea) {
        TareaEntity entity = mapToEntity(tarea);
        TareaEntity savedEntity = repository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public List<Tarea> findByUsuarioAsignado(Tarea usuarioId) {
        return repository.findByUsuarioAsignadoId(usuarioId.getUsuarioAsignadoId())
                .stream()
                .map(this::mapToDomain)
                .toList();
    }

    private TareaEntity mapToEntity(Tarea tarea) {
        TareaEntity entity = new TareaEntity();
        entity.setId(tarea.getId());
        entity.setTitulo(tarea.getTitulo());
        entity.setDescripcion(tarea.getDescripcion());
        entity.setEstado(tarea.getEstado());
        entity.setFechaVencimiento(tarea.getFechaVencimiento());
        entity.setUsuarioCreadorId(tarea.getUsuarioCreadorId());
        entity.setUsuarioAsignadoId(tarea.getUsuarioAsignadoId());
        return entity;
    }

    private Tarea mapToDomain(TareaEntity entity) {
        return new Tarea(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescripcion(),
                entity.getEstado(),
                entity.getFechaVencimiento(),
                entity.getUsuarioCreadorId(),
                entity.getUsuarioAsignadoId()
        );
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDomain);
    }

}
