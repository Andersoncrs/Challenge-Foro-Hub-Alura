package com.anderson.forohub.domain.curso;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ActualizarDatosCurso(
        @NotNull
        Long id,

        @Pattern(regexp = "^(?!\\s*$).+", message = "{nombre.curso.vacio}")
        @Pattern(regexp = "^.{0,200}$", message = "{curso.largo}")
        String nombre
        ) {
}
