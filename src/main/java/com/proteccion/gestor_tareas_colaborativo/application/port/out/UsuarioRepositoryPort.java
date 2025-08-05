package com.proteccion.gestor_tareas_colaborativo.application.port.out;

import com.proteccion.gestor_tareas_colaborativo.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
}
