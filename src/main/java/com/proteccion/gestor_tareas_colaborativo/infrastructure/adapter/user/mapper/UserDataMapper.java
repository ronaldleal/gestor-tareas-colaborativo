package com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.user.mapper;


import com.proteccion.gestor_tareas_colaborativo.domain.user.User;
import com.proteccion.gestor_tareas_colaborativo.infrastructure.adapter.user.data.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDataMapper {
    UserDataMapper INSTANCE = Mappers.getMapper(UserDataMapper.class);

    UserData toUserData(User user);

    User toUserDomain(UserData userData);
}
