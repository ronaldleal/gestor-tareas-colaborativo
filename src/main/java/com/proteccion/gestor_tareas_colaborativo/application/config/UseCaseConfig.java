package com.proteccion.gestor_tareas_colaborativo.application.config;

import com.proteccion.gestor_tareas_colaborativo.application.port.out.PasswordEncoder;
import com.proteccion.gestor_tareas_colaborativo.application.port.out.TareaRepositoryPort;
import com.proteccion.gestor_tareas_colaborativo.application.service.CrearTareaService;
import com.proteccion.gestor_tareas_colaborativo.domain.repository.UserRepository;
import com.proteccion.gestor_tareas_colaborativo.domain.usecase.LoginUserUseCase;
import com.proteccion.gestor_tareas_colaborativo.domain.usecase.RegisterUserUseCase;
import com.proteccion.gestor_tareas_colaborativo.domain.user.gateway.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public CrearTareaService crearTareaService(TareaRepositoryPort tareaRepositoryPort){
        return new CrearTareaService(tareaRepositoryPort);
    }

    @Bean
    protected RegisterUserUseCase registerUserUseCase(UserRepository userRepository,
                                                      JwtProvider jwtProvider,
                                                      PasswordEncoder passwordEncoder) {
        return new RegisterUserUseCase(userRepository, jwtProvider, passwordEncoder);
    }

    @Bean
    protected LoginUserUseCase loginUserUseCase(UserRepository userRepository,
                                                JwtProvider jwtProvider,
                                                PasswordEncoder passwordEncoder) {
        return new LoginUserUseCase(userRepository, jwtProvider, passwordEncoder);
    }
}
