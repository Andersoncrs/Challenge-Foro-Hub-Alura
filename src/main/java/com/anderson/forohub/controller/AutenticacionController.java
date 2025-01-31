package com.anderson.forohub.controller;

import com.anderson.forohub.domain.Usuario.DatosAtutenticacionUsuario;
import com.anderson.forohub.infra.security.DatosJwtToken;
import com.anderson.forohub.service.AutenticacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@Tag(name = "🔐 Autenticación")
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
