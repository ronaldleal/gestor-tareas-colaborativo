package com.proteccion.gestor_tareas_colaborativo.web.mapper;

import com.proteccion.gestor_tareas_colaborativo.domain.user.User;
import com.proteccion.gestor_tareas_colaborativo.web.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDTOMapper {
    UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class);

    User toDomain(UserDTO userDTO);
}
