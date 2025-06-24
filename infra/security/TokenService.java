package com.example.CarrinhoEcommerce.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.CarrinhoEcommerce.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.security}")
    private String secret = "my-secret-key"; // só para garantir que vem corretamente


    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create() // criando o token
                    .withIssuer("api-auth")// nome do nosso token
                    .withSubject(user.getLogin()) // salvando quem é o usuario no token
                    .withExpiresAt(genExpirationDate()) // quando expira
                    .sign(algorithm); // assinatura do token
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro while generating token", exception);
        }
    }

    public String validateToken(String token){
        try {
            System.out.println("🔐 SECRET em uso: " + secret);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var decoded = JWT.require(algorithm)
                    .withIssuer("api-auth")
                    .build()
                    .verify(token);
            String subject = decoded.getSubject();
            System.out.println("✅ Subject extraído do token: " + subject);
            return subject;
        } catch (JWTVerificationException e){
            System.out.println("❌ Erro ao validar token: " + e.getMessage());
            return null;
        }
    }

    // retorna um Insatnt(um instante de tempo)
    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
