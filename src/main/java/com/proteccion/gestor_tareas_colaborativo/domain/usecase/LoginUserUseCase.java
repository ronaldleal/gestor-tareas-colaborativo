package com.proteccion.gestor_tareas_colaborativo.domain.usecase;

import com.proteccion.gestor_tareas_colaborativo.application.port.out.PasswordEncoder;
import com.proteccion.gestor_tareas_colaborativo.domain.repository.UserRepository;
import com.proteccion.gestor_tareas_colaborativo.domain.user.TokenResponse;
import com.proteccion.gestor_tareas_colaborativo.domain.user.User;
import com.proteccion.gestor_tareas_colaborativo.domain.user.exception.UserInternalException;
import com.proteccion.gestor_tareas_colaborativo.domain.user.exception.UserInvalidException;
import com.proteccion.gestor_tareas_colaborativo.domain.user.gateway.JwtProvider;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class LoginUserUseCase {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse login(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UserInvalidException("No account found with the provided username.");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserInvalidException("The password entered is incorrect.");
        }
        try {
            return jwtProvider.generateToken(userName);
        } catch (Exception e) {
            throw new UserInternalException("An issue occurred while generating the authentication token.");
        }
    }
}

