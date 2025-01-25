package com.anderson.forohub.domain.curso;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "cursos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String nombre;

    public Curso(DatosCurso datosCurso) {
        this.nombre = datosCurso.nombre().trim();
    }
}
