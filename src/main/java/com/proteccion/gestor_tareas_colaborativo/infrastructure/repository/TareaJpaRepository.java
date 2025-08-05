package com.proteccion.gestor_tareas_colaborativo.infrastructure.repository;

import com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.persistence.TareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaJpaRepository extends JpaRepository<TareaEntity, Long> {

    List<TareaEntity> findByUsuarioAsignadoId(Long usuarioId);
}