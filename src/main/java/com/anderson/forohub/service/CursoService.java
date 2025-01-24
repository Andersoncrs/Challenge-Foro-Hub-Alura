package com.anderson.forohub.service;

import com.anderson.forohub.domain.curso.Curso;
import com.anderson.forohub.domain.curso.CursoRepository;
import com.anderson.forohub.infra.exception.CursoNoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    private CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso obtenerCursoPorNombre( String nombre){
        return cursoRepository.findByNombre(nombre.trim())
                .orElseThrow(() -> new CursoNoEncontradoException("El curso no ha sido Encontrado: " + nombre));
    }
}
