package ru.projectosnova.identityprovider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class IdentityProvider {

    public boolean validateUser(String username, String password){
        //TODO real user validation
        return username.equalsIgnoreCase("vasya") && password.equals("V@sy@123");
    }

}
