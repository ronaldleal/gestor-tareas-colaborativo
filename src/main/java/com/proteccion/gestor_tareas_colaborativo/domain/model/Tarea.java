package com.proteccion.gestor_tareas_colaborativo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {
    private Long id;
    private String titulo;
    private String descripcion;
    private Estado estado;
    private LocalDate fechaVencimiento;
    private Long usuarioCreadorId;
    private Long usuarioAsignadoId;
}
