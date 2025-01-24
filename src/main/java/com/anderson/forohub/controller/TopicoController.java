package com.anderson.forohub.controller;

import com.anderson.forohub.domain.curso.Curso;
import com.anderson.forohub.domain.topico.DatosTopico;
import com.anderson.forohub.domain.topico.MostrarDatosTopico;
import com.anderson.forohub.service.CursoService;
import com.anderson.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    private final TopicoService topicoService;
    private final CursoService cursoService;

    public TopicoController(TopicoService topicoService, CursoService cursoService) {
        this.topicoService = topicoService;
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<MostrarDatosTopico> crearTopico(@Valid @RequestBody DatosTopico datosTopico,
                                      UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoService.obtenerCursoPorNombre(datosTopico.curso());
        return topicoService.crearTopico(datosTopico, curso, uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MostrarDatosTopico> obtenerTopico(@PathVariable Long id){
        return topicoService.obtenerTopico(id);
    }

    @GetMapping
    public ResponseEntity listarTopicos(Pageable page){
        Pageable pageable = PageRequest.of(page.getPageNumber(), 3, Sort.by("titulo"));

        return topicoService.listarTopicos(pageable);
    }
}
