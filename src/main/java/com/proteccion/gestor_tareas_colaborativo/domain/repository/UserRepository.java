package com.proteccion.gestor_tareas_colaborativo.domain.repository;


import com.proteccion.gestor_tareas_colaborativo.domain.user.User;

public interface UserRepository {
    void save(User user);
    User findByUserName(String userName);
}
