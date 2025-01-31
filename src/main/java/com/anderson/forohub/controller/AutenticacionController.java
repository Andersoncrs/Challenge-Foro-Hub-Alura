package com.anderson.forohub.controller;

import com.anderson.forohub.domain.Usuario.DatosAtutenticacionUsuario;
import com.anderson.forohub.infra.security.DatosJwtToken;
import com.anderson.forohub.service.AutenticacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AutenticacionController {

    private final AutenticacionService autenticacionService;

    public AutenticacionController(AutenticacionService autenticacionService) {
        this.autenticacionService = autenticacionService;
    }

    @PostMapping
    public ResponseEntity<DatosJwtToken> autenticarUsuario(
            @Valid @RequestBody DatosAtutenticacionUsuario datosAtutenticacionUsuario
    ) {
        return  autenticacionService.autenticarUsuario(datosAtutenticacionUsuario);
    }
}
