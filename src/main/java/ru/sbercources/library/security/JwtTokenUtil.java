package ru.sbercources.library.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

  private static final ObjectMapper objectMapper = getDefaultObjectMapper();
  private static final long JWT_TOKEN_VALIDITY = 7 * 24 * 60 * 60; //1 неделя жизни токена
  private final String secret = "zdtlD3JK56m6wTTgsNFhqzjqP";

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, userDetails.toString());
  }

  private static ObjectMapper getDefaultObjectMapper() {
    return new ObjectMapper();
  }

  private String doGenerateToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
//        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public String getUsernameFromToken(String token) {
    String subject = getClaimsFromToken(token, Claims::getSubject);
    JsonNode subjectJson = null;
    try {
      subjectJson = objectMapper.readTree(subject);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    if(subjectJson != null) {
      return subjectJson.get("username").asText();
    } else {
      return null;
    }
  }

  private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }
}
