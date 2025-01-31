package com.anderson.forohub.controller;

import com.anderson.forohub.domain.curso.ActualizarDatosCurso;
import com.anderson.forohub.domain.curso.DatosCurso;
import com.anderson.forohub.domain.curso.MostrarDatosCurso;
import com.anderson.forohub.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cursos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "📑 Curso")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<MostrarDatosCurso> registrarCurso(@Valid @RequestBody DatosCurso datosCurso,
                                                            UriComponentsBuilder uriComponentsBuilder){
        return cursoService.registrarCurso(datosCurso, uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MostrarDatosCurso> mostrarCurso(@PathVariable Long id){
        return cursoService.mostrarCurso(id);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<MostrarDatosCurso>>> listarCursos(Pageable pageable){
        return cursoService.listarCursos(pageable);
    }

    @PutMapping
    public ResponseEntity<MostrarDatosCurso> actuallizarCurso(@Valid @RequestBody ActualizarDatosCurso actualizarDatosCurso){
        return cursoService.actualizarCurso(actualizarDatosCurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id){
        return cursoService.eliminarCurso(id);
    }

}
