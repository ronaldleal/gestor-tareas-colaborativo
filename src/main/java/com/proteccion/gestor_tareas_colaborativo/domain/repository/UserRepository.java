package com.proteccion.gestor_tareas_colaborativo.domain.repository;


import com.proteccion.gestor_tareas_colaborativo.domain.user.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User findByUserName(String userName);
}
