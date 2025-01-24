package com.anderson.forohub.domain.curso;

import jakarta.validation.constraints.Pattern;

public record DatosCurso(

        @Pattern(regexp = "^.{0,200}$", message = "{curso.largo}")
        String nombre
) {
}
