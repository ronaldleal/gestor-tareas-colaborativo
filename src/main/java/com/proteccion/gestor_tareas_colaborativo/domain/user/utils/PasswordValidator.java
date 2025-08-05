package com.proteccion.gestor_tareas_colaborativo.domain.user.utils;

import java.util.regex.Pattern;

public interface PasswordValidator {
    String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    default boolean isStrongPassword(String password) {
        if (password == null) {
            return false;
        }
        return pattern.matcher(password).matches();
    }
}
