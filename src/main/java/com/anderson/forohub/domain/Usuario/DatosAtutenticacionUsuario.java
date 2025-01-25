package com.anderson.forohub.domain.Usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAtutenticacionUsuario(
        @NotBlank
        String nombreUsuario,

        @NotBlank
        String clave
) {
}
