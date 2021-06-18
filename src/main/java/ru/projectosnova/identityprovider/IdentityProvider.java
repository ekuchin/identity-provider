package ru.projectosnova.identityprovider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class IdentityProvider {

    @Autowired
    private IdentityProviderConfig idpConfig;

    public boolean validateUser(String username, String password){
        //TODO real user validation
        return username.equalsIgnoreCase("vasya") && password.equals("V@sy@123");
    }

    /*
    public boolean validateToken(String token){
        return true;
    }
     */

    public String generateToken(boolean isAccess, String username){
        long validity;
        if (isAccess) {
            validity = idpConfig.getAccessLifetime();
        }
        else{
            validity = idpConfig.getRefreshLifetime();
        }

        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
               .setClaims(claims)
               .setSubject(username)
               //.claim("roles",userDetails.getAuthorities())
               .setIssuer(idpConfig.getIssuer())
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
               .signWith(SignatureAlgorithm.HS512, idpConfig.getSecretKey())
               .compact();
    }

}
