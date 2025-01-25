package com.anderson.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ActualizarDatosTopico(
        @NotNull
        Long id,

        @Pattern(regexp = "^.{0,100}$", message = "{titulo.largo}")
        String titulo,

        @Pattern(regexp = "^.+$", message = "{mensaje.vacio}")
        String mensaje
) {
}
