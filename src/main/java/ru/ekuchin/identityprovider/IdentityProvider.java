package ru.ekuchin.identityprovider;

import org.springframework.stereotype.Component;

@Component
public class IdentityProvider {

    public boolean validateUser(String username, String password){
        //TODO real user validation
        return username.equalsIgnoreCase("vasya") && password.equals("V@sy@123");
    }

}
