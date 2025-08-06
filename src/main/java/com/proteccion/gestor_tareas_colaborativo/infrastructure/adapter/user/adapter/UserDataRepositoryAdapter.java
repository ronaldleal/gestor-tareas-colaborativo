package com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.user.adapter;

import com.proteccion.gestor_tareas_colaborativo.domain.repository.UserRepository;
import com.proteccion.gestor_tareas_colaborativo.domain.user.User;
import com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.user.mapper.UserDataMapper;
import com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.user.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDataRepositoryAdapter implements UserRepository {

    private final UserDataRepository userDataRepository;

    @Override
    public User save(User user) {
        userDataRepository.save(UserDataMapper.INSTANCE.toUserData(user));
        return user;
    }

    @Override
    public User findByUserName(String userName) {
        return userDataRepository.findByUserName(userName)
                .map(UserDataMapper.INSTANCE::toUserDomain)
                .orElse(null);
    }
}
