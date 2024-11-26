package com.Ecommerce.LibraryStore.service;


import com.Ecommerce.LibraryStore.model.LocalUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmkey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;
    private Algorithm algorithm;
    private static final String USERNAME_KEY = "USERNAME";

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(algorithmkey);

    }

    public String generateJWT(LocalUser user) {
        // Set the expiration date based on current time + expiryInSeconds
        Date expiryDate = new Date(System.currentTimeMillis() + (1000L * expiryInSeconds));

        // Generate and return the JWT token
        return JWT.create()

                .withClaim(USERNAME_KEY, user.getUsername())
                .withExpiresAt(expiryDate)  // Set the expiration date
                .withIssuer(issuer)  // Set the issuer
                .sign(algorithm);  // Sign the token using the algorithm
    }
    public String getUsername(String token) {
        return JWT.decode(token).getClaim(USERNAME_KEY).asString();

    }


}
