package com.anderson.forohub.service;

import com.anderson.forohub.domain.Usuario.DatosAtutenticacionUsuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {

    private AuthenticationManager authenticationManager;

    public AutenticacionService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity autenticarUsuario(@Valid DatosAtutenticacionUsuario datosAtutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAtutenticacionUsuario.nombreUsuario(),
                datosAtutenticacionUsuario.clave()
        );
        Authentication authentication = authenticationManager.authenticate(authToken);
        return  ResponseEntity.ok().build();
    }
}
