package com.anderson.forohub.service;

import com.anderson.forohub.domain.curso.Curso;
import com.anderson.forohub.domain.curso.CursoRepository;
import com.anderson.forohub.domain.curso.DatosCurso;
import com.anderson.forohub.domain.curso.MostrarDatosCurso;
import com.anderson.forohub.infra.exception.CursoNoEncontradoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Service
public class CursoService {

    private CursoRepository cursoRepository;
    private PagedResourcesAssembler<MostrarDatosCurso> pagedResourcesAssembler;

    public CursoService(CursoRepository cursoRepository,
                        PagedResourcesAssembler<MostrarDatosCurso> pagedResourcesAssembler) {
        this.cursoRepository = cursoRepository;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    public Curso obtenerCursoPorNombre(String nombre){
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

    public ResponseEntity<PagedModel<EntityModel<MostrarDatosCurso>>> listarCursos(Pageable page) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), 5, Sort.by("nombre").ascending());
        Page<MostrarDatosCurso>  listaCursos = cursoRepository.findAll(pageable)
                .map(MostrarDatosCurso::new);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(listaCursos));

    }
}
