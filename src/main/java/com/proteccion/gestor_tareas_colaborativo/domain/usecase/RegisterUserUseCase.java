package com.proteccion.gestor_tareas_colaborativo.domain.usecase;


import com.proteccion.gestor_tareas_colaborativo.application.port.out.PasswordEncoder;
import com.proteccion.gestor_tareas_colaborativo.domain.model.Rol;
import com.proteccion.gestor_tareas_colaborativo.domain.repository.UserRepository;
import com.proteccion.gestor_tareas_colaborativo.domain.user.User;
import com.proteccion.gestor_tareas_colaborativo.domain.user.exception.UserInternalException;
import com.proteccion.gestor_tareas_colaborativo.domain.user.exception.UserInvalidException;
import com.proteccion.gestor_tareas_colaborativo.domain.user.gateway.JwtProvider;
import com.proteccion.gestor_tareas_colaborativo.domain.user.utils.PasswordValidator;
import lombok.RequiredArgsConstructor;

import static ch.qos.logback.core.util.StringUtil.isNullOrEmpty;

@RequiredArgsConstructor
public class RegisterUserUseCase implements PasswordValidator {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if (isNullOrEmpty(user.getUserName()) || isNullOrEmpty(user.getPassword())) {
            throw new UserInvalidException("The username and password fields cannot be blank.");
        }
        if (!isStrongPassword(user.getPassword())) {
            throw new UserInvalidException("The password must be at least 8 characters long and include at least one uppercase letter, " +
                    "one lowercase letter, one number, and one special character.");
        }

        Rol role = user.getRole() != null ? user.getRole() : Rol.USER;

        return userRepository.save(user.toBuilder()
                .password(passwordEncoder.encode(user.getPassword()))
                .role(role)
                .build());
    }

}



