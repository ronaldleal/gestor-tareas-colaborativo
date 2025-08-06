package com.proteccion.gestor_tareas_colaborativo.web.controller;

import com.proteccion.gestor_tareas_colaborativo.domain.usecase.LoginUserUseCase;
import com.proteccion.gestor_tareas_colaborativo.domain.usecase.RegisterUserUseCase;
import com.proteccion.gestor_tareas_colaborativo.domain.usecase.TokenUseCase;
import com.proteccion.gestor_tareas_colaborativo.web.common.BuilderResponseDTO;
import com.proteccion.gestor_tareas_colaborativo.web.dto.UserDTO;
import com.proteccion.gestor_tareas_colaborativo.web.mapper.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController implements BuilderResponseDTO {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final TokenUseCase tokenUseCase;
    private static final String MESSAGE = "Token generated successfully";

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDto) {
        registerUserUseCase.registerUser(UserDTOMapper.INSTANCE.toDomain(userDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(buildResponseDTO(null, "User registered successfully", String.valueOf(HttpStatus.CREATED.value())));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDto) {
        var response = loginUserUseCase.login(userDto.getUserName(), userDto.getPassword());
        return ResponseEntity.ok(buildResponseDTO(response, MESSAGE, String.valueOf(HttpStatus.OK.value())));
    }


    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestParam String userName) {
        var token = tokenUseCase.generateTokenOnly(userName);
        return ResponseEntity.ok(
                buildResponseDTO(token.getToken(), MESSAGE, String.valueOf(HttpStatus.OK.value()))
        );
    }
}
