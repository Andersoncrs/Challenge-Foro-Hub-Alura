package com.anderson.forohub.domain.topico;

import jakarta.validation.constraints.Pattern;

public record ActualizarDatosTopico(
        Long id,

        @Pattern(regexp = "^.{0,100}$", message = "{titulo.largo}")
        String titulo,

        String mensaje
) {
}
