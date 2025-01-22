package com.anderson.forohub.domain.topico;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record DatosTopico(

        @NotBlank
        @Min(5)
        @Max(100)
        String titulo,

        @NotBlank
        String curso,

        @NotBlank
        String mensaje,
        @NotBlank
        String motivo
) {
}
