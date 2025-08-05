package com.proteccion.gestor_tareas_colaborativo.domain.usecase;


import com.proteccion.gestor_tareas_colaborativo.application.port.out.PasswordEncoder;
import com.proteccion.gestor_tareas_colaborativo.domain.user.TokenResponse;
import com.proteccion.gestor_tareas_colaborativo.domain.user.User;
import com.proteccion.gestor_tareas_colaborativo.domain.user.exception.UserInternalException;
import com.proteccion.gestor_tareas_colaborativo.domain.user.exception.UserInvalidException;
import com.proteccion.gestor_tareas_colaborativo.domain.user.gateway.JwtProvider;
import com.proteccion.gestor_tareas_colaborativo.domain.user.utils.PasswordValidator;
import com.proteccion.gestor_tareas_colaborativo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import static ch.qos.logback.core.util.StringUtil.isNullOrEmpty;

@RequiredArgsConstructor
public class RegisterUserUseCase implements PasswordValidator {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse registerUserAndGenerateToken(User user) {
        if (isNullOrEmpty(user.getUserName()) || isNullOrEmpty(user.getPassword())) {
            throw new UserInvalidException("The username and password fields cannot be blank.");
        }
        if (!isStrongPassword(user.getPassword())) {
            throw new UserInvalidException("The password must be at least 8 characters long and include at least one uppercase letter, " +
                    "one lowercase letter, one number, and one special character.");
        }
        try {
            userRepository.save(user.toBuilder().password(passwordEncoder.encode(user.getPassword())).build());
        } catch (Exception e) {
            throw new UserInternalException("Failed to register the user: " + e.getMessage());
        }
        try {
            return jwtProvider.generateToken(user.getUserName());
        } catch (Exception e) {
            throw new UserInternalException("An error occurred while generating the authentication token: " + e.getMessage());
        }
    }
}

