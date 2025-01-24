package com.anderson.forohub.domain.curso;

public record MostrarDatosCurso(
        Long id,
        String nombre
) {
    public MostrarDatosCurso(Curso curso) {
        this(curso.getId(),
            curso.getNombre()
        );
    }
}
