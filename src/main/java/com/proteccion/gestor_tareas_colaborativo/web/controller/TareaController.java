package com.proteccion.gestor_tareas_colaborativo.web.controller;

import com.proteccion.gestor_tareas_colaborativo.application.service.CrearTareaUseCase;
import com.proteccion.gestor_tareas_colaborativo.domain.model.Estado;
import com.proteccion.gestor_tareas_colaborativo.domain.model.Tarea;
import com.proteccion.gestor_tareas_colaborativo.web.dto.TareaRequestDto;
import com.proteccion.gestor_tareas_colaborativo.web.dto.TareaResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tareas")
@AllArgsConstructor
public class TareaController {
    private final CrearTareaUseCase crearTareaUseCase;

    @PostMapping
    public ResponseEntity<TareaResponseDto> crearTarea(@RequestBody @Valid TareaRequestDto request) {
        Tarea tarea = new Tarea(null, request.getTitulo(), request.getDescripcion(),
                Estado.POR_HACER, request.getFechaVencimiento(), request.getUsuarioCreadorId(),
                request.getUsuarioAsignadoId());
        Tarea creada = crearTareaUseCase.crearTarea(tarea);
        return ResponseEntity.ok(new TareaResponseDto(creada));
    }
}
