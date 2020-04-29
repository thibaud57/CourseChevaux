package com.ifaproject.CourseChevaux.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    //Retourne le corp du token
    public Claims extractionDuCorpDuToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    //Retourne un token
    public String generateToken(UserDetails userDetail) {
        Map<String, Object> tokenData = new HashMap<>();

        //inscrit le role admin dans le token
        boolean isAdmin = false;
        for (GrantedAuthority role: userDetail.getAuthorities()) {
            if(role.getAuthority().equals("ROLE_ADMIN")){
                isAdmin = true;
            }
        }
        tokenData.put("admin", isAdmin);

        return Jwts.builder()
                .setClaims(tokenData)
                .setSubject(userDetail.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    //Retourne vrai si le token n'a pas dépassé la date d'expiration
    private Boolean tokenNonDepasseDateExpiration(String token) {
        return extractionDuCorpDuToken(token)
                .getExpiration()
                .before(new Date());
    }
    //Retourne vrai si le nom de l'utilisateur tentant de se connecter correspond
//au subject du corp du token et si la date d'expiration n'est pas passée.
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractionDuCorpDuToken(token).getSubject();
        return (username.equals(userDetails.getUsername()) && !tokenNonDepasseDateExpiration(token));
    }


}
