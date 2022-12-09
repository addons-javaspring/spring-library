package com.fazz.library.config.jwt;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fazz.library.service.auth.UserDetailsImpl;

@Component
public class JwtUtil {
  @Value("${fazz.jwt.secret-key}")
  private String SECRET_KEY;

  @Value("${fazz.jwt.expiration-ms}")
  private Long EXPIRED_MS;

  public String generateToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    String base64Key = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());

    return JWT.create()
        .withSubject("auth")
        .withClaim("email", userPrincipal.getUsername())
        .withIssuedAt(new Date())
        .withExpiresAt(new Date((new Date()).getTime() + EXPIRED_MS))
        .sign(Algorithm.HMAC256(base64Key));
  }

  public String getUsernameFromToken(String jwtToken) {
    String base64Secret = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());

    return JWT.require(Algorithm.HMAC256(base64Secret))
        .build()
        .verify(jwtToken)
        .getClaims().get("email").asString();
  }

  public boolean validateToken(String jwtToken) {
    String base64Secret = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    JWT.require(Algorithm.HMAC256(base64Secret)).build().verify(jwtToken);
    return true;
  }
}
