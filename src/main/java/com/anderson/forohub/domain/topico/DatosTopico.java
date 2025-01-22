package com.anderson.forohub.domain.topico;


import com.anderson.forohub.validate.ValidCurso;
import com.anderson.forohub.validate.ValidMotivo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosTopico(

        @NotBlank
        @Pattern(regexp = "^.{0,100}$", message = "{titulo.largo}")
        String titulo,

        @NotNull
        @Pattern(regexp = "^.{0,200}$", message = "{curso.largo}")
        @ValidCurso
        String curso,

        @NotBlank
        String mensaje,

        @NotNull
        @ValidMotivo
        @Pattern(regexp = "^.{0,50}$", message = "{motivo.largo}")
        String motivo
) {
}
