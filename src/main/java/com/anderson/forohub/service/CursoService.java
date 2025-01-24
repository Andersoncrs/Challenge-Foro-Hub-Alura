package com.anderson.forohub.service;

import com.anderson.forohub.domain.curso.Curso;
import com.anderson.forohub.domain.curso.CursoRepository;
import com.anderson.forohub.domain.curso.DatosCurso;
import com.anderson.forohub.domain.curso.MostrarDatosCurso;
import com.anderson.forohub.infra.exception.CursoNoEncontradoException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CursoService {

    private CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso obtenerCursoPorNombre( String nombre){
        return cursoRepository.findByNombre(nombre.trim())
                .orElseThrow(() -> new CursoNoEncontradoException("El curso no ha sido Encontrado: " + nombre));
    }

    public ResponseEntity<MostrarDatosCurso> registrarCurso(DatosCurso datosCurso, UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = cursoRepository.save(new Curso(datosCurso));
        MostrarDatosCurso mostrarDatosCurso = new MostrarDatosCurso(curso);
        URI location = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(mostrarDatosCurso.id()).toUri();
        return ResponseEntity.created(location).body(mostrarDatosCurso);

    }

    public ResponseEntity<MostrarDatosCurso> mostrarCurso(Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        MostrarDatosCurso mostrarDatosCurso = new MostrarDatosCurso(curso);
        return ResponseEntity.ok(mostrarDatosCurso);
    }
}
