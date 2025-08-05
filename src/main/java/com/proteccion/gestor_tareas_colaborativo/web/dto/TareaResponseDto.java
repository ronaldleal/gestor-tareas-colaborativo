package com.proteccion.gestor_tareas_colaborativo.web.dto;

import com.proteccion.gestor_tareas_colaborativo.domain.model.Estado;
import com.proteccion.gestor_tareas_colaborativo.domain.model.Tarea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TareaResponseDto {
    private Long id;
    private String titulo;
    private String descripcion;
    private Estado estado;
    private LocalDate fechaVencimiento;
    private Long usuarioCreadorId;
    private Long usuarioAsignadoId;

    public TareaResponseDto(Tarea tarea) {
        this.id = tarea.getId();
        this.titulo = tarea.getTitulo();
        this.descripcion = tarea.getDescripcion();
        this.estado = tarea.getEstado();
        this.fechaVencimiento = tarea.getFechaVencimiento();
        this.usuarioCreadorId = tarea.getUsuarioCreadorId();
        this.usuarioAsignadoId = tarea.getUsuarioAsignadoId();
    }
}
