package com.anderson.forohub.domain.topico;

public record MostrarDatosTopico(
        Long id,
        String titulo,
        String curso,
        String mensaje,
        String motivo
) {
    public MostrarDatosTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getCurso().getNombre(),
                topico.getMensaje(),
                topico.getMotivo().toString()
        );
    }
}
