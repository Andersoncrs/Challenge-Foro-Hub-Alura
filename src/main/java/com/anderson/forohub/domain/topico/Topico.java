package com.anderson.forohub.domain.topico;

import com.anderson.forohub.domain.curso.Curso;
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

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private String mensaje;
    @Enumerated(EnumType.STRING)
    private Motivo motivo;

    public Topico(DatosTopico datosTopico, Curso curso) {
        this.motivo = Motivo.fromInputUser(datosTopico.motivo());
        this.mensaje = datosTopico.mensaje().trim();
        this.curso = curso;
        this.titulo = datosTopico.titulo().trim();
    }
}
