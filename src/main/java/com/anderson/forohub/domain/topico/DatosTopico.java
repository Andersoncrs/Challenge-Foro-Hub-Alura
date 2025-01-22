package com.anderson.forohub.domain.topico;


import com.anderson.forohub.validate.ValidCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosTopico(

        @NotBlank
        @Pattern(regexp = "^.{0,100}$", message = "{titulo.largo}")
        String titulo,

        @NotNull
        @ValidCurso
        String curso,

        @NotBlank
        String mensaje,

        @NotNull
        Motivo motivo
) {
}
