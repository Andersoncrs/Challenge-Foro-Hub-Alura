package com.anderson.forohub.controller;

import com.anderson.forohub.domain.Usuario.DatosAtutenticacionUsuario;
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

    private AuthenticationManager authenticationManager;

    public AutenticacionController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<Void> autenticarUsuario(
            @Valid @RequestBody DatosAtutenticacionUsuario datosAtutenticacionUsuario
    ) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAtutenticacionUsuario.nombreUsuario(),
                datosAtutenticacionUsuario.clave()
        );
        Authentication authentication = authenticationManager.authenticate(authToken);
        return ResponseEntity.ok().build();
    }
}
