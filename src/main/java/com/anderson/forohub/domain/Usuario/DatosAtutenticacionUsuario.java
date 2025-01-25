package com.anderson.forohub.domain.Usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAtutenticacionUsuario(
        @NotBlank
        // @Pattern(regexp = "^(?=[a-zA-Z0-9._]{3,16}$)(?!.*[_.]{2})[^_.].*[^_.]$", message = "{usuario.no.valido}")
        String nombre,

        @NotBlank
        // @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{5,}$", message = "{clave.invalida}")
        String clave
) {
}
