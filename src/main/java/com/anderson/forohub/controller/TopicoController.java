package com.anderson.forohub.controller;

import com.anderson.forohub.domain.topico.DatosTopico;
import com.anderson.forohub.domain.topico.MostrarDatosTopico;
import com.anderson.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    private TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<MostrarDatosTopico> crearTopico(@Valid @RequestBody DatosTopico datosTopico,
                                      UriComponentsBuilder uriComponentsBuilder){

        return topicoService.crearTopico(datosTopico, uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MostrarDatosTopico> obtenerTopico(@PathVariable Long id){
        return topicoService.obtenerTopico(id);
    }
}
