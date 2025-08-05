package com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.user.repository;

import com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.user.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUserName(String userName);
}
