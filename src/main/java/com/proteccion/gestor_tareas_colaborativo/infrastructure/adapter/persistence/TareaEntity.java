package com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.persistence;

import com.proteccion.gestor_tareas_colaborativo.domain.model.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tareas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TareaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private LocalDate fechaVencimiento;
    private Long usuarioCreadorId;
    private Long usuarioAsignadoId;
}
