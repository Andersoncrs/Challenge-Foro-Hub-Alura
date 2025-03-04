package com.anderson.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record MostrarDatosTopico(
        Long id,
        String titulo,
        String autor,
        String curso,
        String mensaje,
        String motivo,

        @JsonFormat(pattern = "yyyy MMMM dd HH:mm a")
        LocalDateTime fechaCreacion,

        boolean editado,
        boolean solucionado
) {
    public MostrarDatosTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getUsuario().getNombreUsuario(),
                topico.getCurso().getNombre(),
                topico.getMensaje(),
                topico.getMotivo().toString(),
                topico.getFechaCreacion(),
                topico.isEditado(),
                topico.isSolucionado()
        );
    }
}
