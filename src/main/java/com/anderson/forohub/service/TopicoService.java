package com.anderson.forohub.service;

import com.anderson.forohub.domain.curso.Curso;
import com.anderson.forohub.domain.topico.*;
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
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final PagedResourcesAssembler<MostrarDatosTopico> pagedResourcesAssembler;

    public TopicoService(TopicoRepository topicoRepository,
                         PagedResourcesAssembler<MostrarDatosTopico> pagedResourcesAssembler) {
        this.topicoRepository = topicoRepository;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    public ResponseEntity<MostrarDatosTopico> crearTopico(DatosTopico datosTopico,
                                                          Curso curso,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.save(new Topico(datosTopico, curso));
        MostrarDatosTopico mostrarDatosTopico = new MostrarDatosTopico(topico);
        URI location = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(location).body(mostrarDatosTopico);
    }

    public ResponseEntity<MostrarDatosTopico> obtenerTopico(Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        MostrarDatosTopico mostrarDatosTopico = new MostrarDatosTopico(topico);
        return ResponseEntity.ok(mostrarDatosTopico);
    }

    public ResponseEntity<PagedModel<EntityModel<MostrarDatosTopico>>> listarTopicos(Pageable page) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), 3, Sort.by("fechaCreacion").descending());
        Page<MostrarDatosTopico> listaTopicos = topicoRepository.findAll(pageable)
                .map(MostrarDatosTopico::new);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(listaTopicos));
    }

    public ResponseEntity<MostrarDatosTopico> actualizarTopico(ActualizarDatosTopico actualizarDatosTopico) {
        Topico topico = topicoRepository.getReferenceById(actualizarDatosTopico.id());
        if (actualizarDatosTopico.titulo() != null) {
            topico.setTitulo(actualizarDatosTopico.titulo().trim());
        }
        if (actualizarDatosTopico.mensaje() != null) {
            topico.setMensaje(actualizarDatosTopico.mensaje().trim());
        }
        topico.setEditado(true);
        Topico topicoActualizado = topicoRepository.save(topico);
        return ResponseEntity.ok(new MostrarDatosTopico(topicoActualizado));
    }

    public ResponseEntity<Void> eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
