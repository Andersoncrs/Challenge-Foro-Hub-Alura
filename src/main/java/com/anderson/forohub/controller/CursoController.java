package com.anderson.forohub.controller;

import com.anderson.forohub.domain.curso.DatosCurso;
import com.anderson.forohub.domain.curso.MostrarDatosCurso;
import com.anderson.forohub.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cursos")
public class CursoController {

    private CursoService cursoService;

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

}
