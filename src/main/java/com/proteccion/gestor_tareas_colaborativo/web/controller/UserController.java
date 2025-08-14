package com.proteccion.gestor_tareas_colaborativo.web.controller;

import com.proteccion.gestor_tareas_colaborativo.domain.usecase.LoginUserUseCase;
import com.proteccion.gestor_tareas_colaborativo.domain.usecase.RegisterUserUseCase;
import com.proteccion.gestor_tareas_colaborativo.domain.usecase.TokenUseCase;
import com.proteccion.gestor_tareas_colaborativo.web.common.BuilderResponseDTO;
import com.proteccion.gestor_tareas_colaborativo.web.dto.UserDTO;
import com.proteccion.gestor_tareas_colaborativo.web.mapper.UserDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Registrar un nuevo usuario", description = "Registra un nuevo usuario con los detalles proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDto) {
        registerUserUseCase.registerUser(UserDTOMapper.INSTANCE.toDomain(userDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(buildResponseDTO(null, "User registered successfully", String.valueOf(HttpStatus.CREATED.value())));
    }

    @Operation(summary = "Iniciar sesión", description = "Autentica un usuario con el nombre de usuario y la contraseña")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDto) {
        var response = loginUserUseCase.login(userDto.getUserName(), userDto.getPassword());
        return ResponseEntity.ok(buildResponseDTO(response, MESSAGE, String.valueOf(HttpStatus.OK.value())));
    }

    @Operation(summary = "Generar token JWT", description = "Genera un token JWT para un usuario autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token generado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestParam String userName) {
        var token = tokenUseCase.generateTokenOnly(userName);
        return ResponseEntity.ok(
                buildResponseDTO(token.getToken(), MESSAGE, String.valueOf(HttpStatus.OK.value()))
        );
    }
}
