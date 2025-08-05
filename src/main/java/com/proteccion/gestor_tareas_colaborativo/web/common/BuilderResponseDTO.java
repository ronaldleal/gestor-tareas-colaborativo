package com.proteccion.gestor_tareas_colaborativo.web.common;

public interface BuilderResponseDTO {
    default  <T> ResponseDTO<T> buildResponseDTO(T data, String message, String status) {
        return ResponseDTO.<T>builder()
                .data(data)
                .message(message)
                .status(status)
                .build();
    }
}
