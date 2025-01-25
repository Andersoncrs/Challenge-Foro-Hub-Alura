package com.anderson.forohub.service;

import com.anderson.forohub.domain.Usuario.DatosAtutenticacionUsuario;
import com.anderson.forohub.infra.exception.CredencialesInvalidasException;
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
                datosAtutenticacionUsuario.nombre(),
                datosAtutenticacionUsuario.clave()
        );
        try {
            Authentication authentication = authenticationManager.authenticate(authToken);
        }catch (Exception e){
            throw new CredencialesInvalidasException("Las credenciales Ingresadas no son validas");
        }
        return ResponseEntity.ok().build();
    }
}
