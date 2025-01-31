package com.anderson.forohub.infra.security;

import com.anderson.forohub.domain.Usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    public DecodedJWT VerificarToken(String jwtToken){
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(emisorToken)
                    .build();
            decodedJWT = verifier.verify(jwtToken);
            return  decodedJWT;
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("El Token ingresado no es Valido");
        }
    }

    public String obtenerSubject(DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }

    private Instant establecerExpiracionToken() {
        return Instant.now().plusSeconds(1800);
    }
}
