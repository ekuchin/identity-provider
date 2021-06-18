package ru.projectosnova.identityprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IdentityProviderConfig {

    @Value("${jwt.access.validity.seconds}")
    private long accessLifetime;

    @Value("${jwt.refresh.validity.seconds}")
    private long refreshLifetime;

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    public long getAccessLifetime(){
        return accessLifetime;
    }

    public long getRefreshLifetime(){
        return refreshLifetime;
    }

    public String getSecretKey(){
        return secretKey;
    }

    public String getIssuer(){
        return issuer;
    }

}
