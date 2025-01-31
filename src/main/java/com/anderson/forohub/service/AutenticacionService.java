package com.anderson.forohub.service;

import com.anderson.forohub.domain.Usuario.DatosAtutenticacionUsuario;
import com.anderson.forohub.domain.Usuario.Usuario;
import com.anderson.forohub.infra.exception.CredencialesInvalidasException;
import com.anderson.forohub.infra.security.DatosJwtToken;
import com.anderson.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacionService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public ResponseEntity<DatosJwtToken> autenticarUsuario(
            @Valid DatosAtutenticacionUsuario datosAtutenticacionUsuario) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAtutenticacionUsuario.nombre(),
                datosAtutenticacionUsuario.clave()
        );
        try {
            Authentication authentication = authenticationManager.authenticate(authToken);
            String jwtToken = tokenService.generarToken((Usuario) authentication.getPrincipal());
            return ResponseEntity.ok(new DatosJwtToken(jwtToken));
        }catch (Exception e){
            throw new CredencialesInvalidasException("Las credenciales Ingresadas no son validas");
        }
    }
}
