package com.anderson.forohub.domain.topico;

import com.anderson.forohub.domain.curso.Curso;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Setter
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Setter
    private String mensaje;

    @Enumerated(EnumType.STRING)
    private Motivo motivo;

    @JoinColumn(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Setter
    private boolean editado;

    @Setter
    private boolean solucionado;

    public Topico(DatosTopico datosTopico, Curso curso) {
        this.motivo = Motivo.fromInputUser(datosTopico.motivo());
        this.mensaje = datosTopico.mensaje().trim();
        this.curso = curso;
        this.titulo = datosTopico.titulo().trim();
        this.fechaCreacion = LocalDateTime.now();
        this.editado = false;
        this.solucionado = false;
    }
}
