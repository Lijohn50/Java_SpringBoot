package org.practice.security_1;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JWTService {

    private String privateKey = "";

    public JWTService() throws NoSuchAlgorithmException {

        KeyGenerator generate = KeyGenerator.getInstance("HmacSHA256");
        SecretKey sk = generate.generateKey();
        privateKey = Base64.getEncoder().encodeToString(sk.getEncoded());

    }

    public String generateToken(String username) {

        Map<String , Objects> claim = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claim)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .and()
                .signWith(getKey())
                .compact();
    }

    public Key getKey(){

        byte[] key = Decoders.BASE64.decode(privateKey);
        return Keys.hmacShaKeyFor(key);
    }
}
