package com.orient.library.auth;

import com.orient.library.dto.request.RefreshTokenRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class TokenManager {
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final int ACCESS_TOKEN_VALIDITY_PERIOD = 60 * 60 * 1000;
    private static final int REFRESH_TOKEN_VALIDITY_PERIOD = 180 * 60 * 1000;

    public Map<String, String> generateToken(String userEmail) {
        Map<String, String> tokens = new HashMap<>();
        // Generate AccessToken
        String accessToken = Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_PERIOD))
                .signWith(key)
                .compact();

        // Generate Refresh Token
        String refreshToken = Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_PERIOD))
                .signWith(key)
                .compact();

        //Set Map
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        return tokens;
    }

    public Map<String, String> refreshToken(RefreshTokenRequestDto refreshToken){
        //check RefreshToken expired
        if(tokenValidate(refreshToken.getRefreshToken())){
            String userEmail = getEmailFromToken(refreshToken.getRefreshToken());
            return generateToken(userEmail);
        }
        throw new JwtException("Token is invalid!");
    }

    public boolean tokenValidate(String token) {
        if (getEmailFromToken(token) != null && isExpired(token)) return true;
        return false;
    }

    public String getEmailFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
