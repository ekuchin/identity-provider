package ru.ekuchin.identityprovider.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ekuchin.identityprovider.IdentityProviderConfig;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTToken {
    private String token;

    @Autowired
    private IdentityProviderConfig idpConfig;

    public void generate(boolean isAccess, String username){

        long validity;
        if (isAccess) {
            validity = idpConfig.getAccessLifetime();
        }
        else{
            validity = idpConfig.getRefreshLifetime();
        }

        Map<String, Object> claims = new HashMap<>();
        this.token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                //.claim("roles",userDetails.getAuthorities())
                .setIssuer(idpConfig.getIssuer())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
                .signWith(SignatureAlgorithm.HS512, idpConfig.getSecretKey())
                .compact();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isValid(){
          return getIssuer().equals(idpConfig.getIssuer()) && !isExpired();
    }

    public <T> T getClaimFromToken(Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .setSigningKey(idpConfig.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public String getIssuer(){
        return getClaimFromToken(Claims::getIssuer);
    }

    public String getUserName(){
        return getClaimFromToken(Claims::getSubject);
    }

    public Date getExpirationDate() {
        return getClaimFromToken(Claims::getExpiration);
    }

    public Boolean isExpired() {
        final Date expiration = getExpirationDate();
        return expiration.before(new Date());
    }

}
