package com.proteccion.gestor_tareas_colaborativo.web.controller;

import com.proteccion.gestor_tareas_colaborativo.application.service.CrearTareaUseCase;
import com.proteccion.gestor_tareas_colaborativo.domain.model.Estado;
import com.proteccion.gestor_tareas_colaborativo.domain.model.Tarea;
import com.proteccion.gestor_tareas_colaborativo.web.dto.TareaRequestDto;
import com.proteccion.gestor_tareas_colaborativo.web.dto.TareaResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@AllArgsConstructor
public class TareaController {
    private final CrearTareaUseCase crearTareaUseCase;

    @PostMapping("/crear")
    public ResponseEntity<TareaResponseDto> crearTarea(@RequestBody @Valid TareaRequestDto request) {
        Tarea tarea = new Tarea(null, request.getTitulo(), request.getDescripcion(),
                Estado.POR_HACER, request.getFechaVencimiento(), request.getUsuarioCreadorId(),
                request.getUsuarioAsignadoId());
        Tarea creada = crearTareaUseCase.crearTarea(tarea);
        return ResponseEntity.ok(new TareaResponseDto(creada));
    }

    @GetMapping("/asignadas/{usuarioId}")
    public ResponseEntity<List<TareaResponseDto>> getTareasPorUsuarioAsignado(
            @PathVariable Long usuarioId) {

        List<Tarea> tareas = crearTareaUseCase.findByUsuarioAsignado(usuarioId);

        List<TareaResponseDto> tareasDto = mapToResponseDto(tareas);

        return ResponseEntity.ok(tareasDto);
    }



    private List<TareaResponseDto> mapToResponseDto(List<Tarea> tareas) {
        return tareas.stream()
                .map(tarea -> new TareaResponseDto(
                        tarea.getId(),
                        tarea.getTitulo(),
                        tarea.getDescripcion(),
                        tarea.getEstado(),
                        tarea.getFechaVencimiento(),
                        tarea.getUsuarioCreadorId(),
                        tarea.getUsuarioAsignadoId()
                ))
                .toList();
    }


}
