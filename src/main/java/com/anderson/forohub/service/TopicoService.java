package com.anderson.forohub.service;

import com.anderson.forohub.domain.topico.DatosTopico;
import com.anderson.forohub.domain.topico.Topico;
import com.anderson.forohub.domain.topico.TopicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    private TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

//    public ResponseEntity crearTopico(DatosTopico datosTopico){
//    }
}
