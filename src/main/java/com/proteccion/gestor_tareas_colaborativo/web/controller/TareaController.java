package com.proteccion.gestor_tareas_colaborativo.web.controller;

import com.proteccion.gestor_tareas_colaborativo.application.service.CrearTareaUseCase;
import com.proteccion.gestor_tareas_colaborativo.domain.model.Estado;
import com.proteccion.gestor_tareas_colaborativo.domain.model.Tarea;
import com.proteccion.gestor_tareas_colaborativo.web.dto.TareaRequestDto;
import com.proteccion.gestor_tareas_colaborativo.web.dto.TareaResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Crear una nueva tarea", description = "Crea una tarea y la asigna a un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @PostMapping("/crear")
    public ResponseEntity<TareaResponseDto> crearTarea(@RequestBody @Valid TareaRequestDto request) {
        Tarea tarea = new Tarea(null, request.getTitulo(), request.getDescripcion(),
                Estado.POR_HACER, request.getFechaVencimiento(), request.getUsuarioCreadorId(),
                request.getUsuarioAsignadoId());
        Tarea creada = crearTareaUseCase.crearTarea(tarea);
        return ResponseEntity.ok(new TareaResponseDto(creada));
    }

    @Operation(summary = "Obtener tareas asignadas a un usuario", description = "Devuelve las tareas asignadas a un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tareas obtenidas exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/asignadas/{usuarioId}")
    public ResponseEntity<List<TareaResponseDto>> getTareasPorUsuarioAsignado(
            @PathVariable Long usuarioId) {

        List<Tarea> tareas = crearTareaUseCase.findByUsuarioAsignado(usuarioId);

        List<TareaResponseDto> tareasDto = mapToResponseDto(tareas);

        return ResponseEntity.ok(tareasDto);
    }

    @Operation(summary = "Modificar tarea", description = "Modifica los detalles de una tarea existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea modificada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TareaResponseDto> modificarTarea(
            @PathVariable Long id,
            @RequestBody @Valid TareaRequestDto tareaUpdateDto) {

        Tarea tareaModificada = new Tarea(
                id,
                tareaUpdateDto.getTitulo(),
                tareaUpdateDto.getDescripcion(),
                null,
                tareaUpdateDto.getFechaVencimiento(),
                null,
                tareaUpdateDto.getUsuarioAsignadoId()
        );

        Tarea tareaActualizada = crearTareaUseCase.modificarTarea(id, tareaModificada);

        TareaResponseDto response = new TareaResponseDto(
                tareaActualizada.getId(),
                tareaActualizada.getTitulo(),
                tareaActualizada.getDescripcion(),
                tareaActualizada.getEstado(),
                tareaActualizada.getFechaVencimiento(),
                tareaActualizada.getUsuarioCreadorId(),
                tareaActualizada.getUsuarioAsignadoId()
        );

        return ResponseEntity.ok(response);
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
