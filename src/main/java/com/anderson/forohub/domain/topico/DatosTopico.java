package com.anderson.forohub.domain.topico;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosTopico(

        @NotBlank
        @Pattern(regexp = "^.{0,100}$", message = "{titulo.largo}")
        String titulo,

        @NotBlank
        String curso,

        @NotBlank
        String mensaje,
        @NotBlank
        String motivo
) {
}
