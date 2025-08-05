package com.proteccion.gestor_tareas_colaborativo.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TareaRequestDto {
    @NotBlank
    private String titulo;

    @NotBlank
    private String descripcion;

    @NotNull
    private LocalDate fechaVencimiento;

    @NotNull
    private Long usuarioCreadorId;

    @NotNull
    private Long usuarioAsignadoId;
}
