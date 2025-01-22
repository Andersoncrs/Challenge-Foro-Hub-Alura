package com.anderson.forohub.service;

import com.anderson.forohub.domain.topico.DatosTopico;
import com.anderson.forohub.domain.topico.MostrarDatosTopico;
import com.anderson.forohub.domain.topico.Topico;
import com.anderson.forohub.domain.topico.TopicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class TopicoService {

    private TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public ResponseEntity<MostrarDatosTopico> crearTopico(DatosTopico datosTopico, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(new Topico(datosTopico));
        MostrarDatosTopico mostrarDatosTopico = new MostrarDatosTopico(topico);
        URI location = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(location).body(mostrarDatosTopico);
    }

    public ResponseEntity<MostrarDatosTopico> obtenerTopico(Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        MostrarDatosTopico mostrarDatosTopico = new MostrarDatosTopico(topico);
        return ResponseEntity.ok(mostrarDatosTopico);
    }
}
