package ru.ekuchin.identityprovider;

import org.springframework.stereotype.Component;

@Component
public class IdentityProvider {

    public boolean validateUser(String username, String password){
        //TODO real user validation
        boolean isVasya = username.equalsIgnoreCase("vasya") && password.equals("V@sy@123");
        boolean isPetya = username.equalsIgnoreCase("petya") && password.equals("Pety@123");
        return (isVasya || isPetya);
    }

}
