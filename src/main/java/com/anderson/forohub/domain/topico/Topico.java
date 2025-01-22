package com.anderson.forohub.domain.topico;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Curso curso;
    private String mensaje;
    @Enumerated(EnumType.STRING)
    private Motivo motivo;

    public Topico(DatosTopico datosTopico) {
        this.motivo = Motivo.fromInputUser(datosTopico.motivo());
        this.mensaje = datosTopico.mensaje().trim();
        this.curso = Curso.fromInputUser(datosTopico.curso());
        this.titulo = datosTopico.titulo().trim();
    }
}
