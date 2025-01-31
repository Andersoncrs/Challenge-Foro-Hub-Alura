package com.anderson.forohub.infra.security;

import com.anderson.forohub.domain.Usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    @Value("${api.security.secret")
    private String secret;
    private final String emisorToken = "foro-hub";

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer(emisorToken)
                    .withSubject(usuario.getNombreUsuario())
                    .withClaim("id", usuario.getId())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(establecerExpiracionToken())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Hubo un Error al momento de crear el Token", exception);
        }
    }

    private Instant establecerExpiracionToken(){
        return Instant.now().plusSeconds(1800);
    }
}
