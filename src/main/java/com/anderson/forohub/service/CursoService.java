package com.anderson.forohub.service;

import com.anderson.forohub.domain.curso.*;
import com.anderson.forohub.infra.exception.CursoNoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
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

    private final CursoRepository cursoRepository;
    private final PagedResourcesAssembler<MostrarDatosCurso> pagedResourcesAssembler;

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

    public ResponseEntity<MostrarDatosCurso> actualizarCurso( ActualizarDatosCurso actualizarDatosCurso) {
        Curso curso = cursoRepository.getReferenceById(actualizarDatosCurso.id());
        if(actualizarDatosCurso.nombre() != null){
            curso.setNombre(actualizarDatosCurso.nombre());
        }
        Curso cursoActualizado = cursoRepository.save(curso);
        return ResponseEntity.ok(new MostrarDatosCurso(cursoActualizado));
    }

    public ResponseEntity<Void> eliminarCurso(Long id) {
        if(!cursoRepository.existsById(id)){
            throw new EntityNotFoundException();
        }
        cursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
