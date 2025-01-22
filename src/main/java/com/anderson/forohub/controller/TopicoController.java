package com.anderson.forohub.controller;

import com.anderson.forohub.domain.topico.DatosTopico;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @PostMapping
    public ResponseEntity crearTopico(@Valid @RequestBody DatosTopico datosTopico){
        System.out.println(datosTopico);
        return ResponseEntity.ok().build();
    }
}
