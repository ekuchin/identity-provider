package ru.projectosnova.identityprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import ru.projectosnova.identityprovider.jwt.JWTAuthRequest;
import ru.projectosnova.identityprovider.jwt.JWTTokenPairResponse;

@RestController
public class IdentityProviderController {

    @Autowired
    private IdentityProvider identityProvider;

/*
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }

 */

    @ResponseBody
    @PostMapping(value = "/auth")
    public ResponseEntity<?> authUser (@RequestBody JWTAuthRequest authRequest) {

            if (identityProvider.validateUser(authRequest.getUsername(),authRequest.getPassword())){
                String accessToken = identityProvider.generateToken(true, authRequest.getUsername());
                String refreshToken=identityProvider.generateToken(false, authRequest.getUsername());
                return ResponseEntity.ok(new JWTTokenPairResponse(accessToken,refreshToken));
            }
            else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User or password is incorrect");
    }

    /*
    public ResponseEntity<?> refreshTokenPair(@RequestBody RefreshToken refreshToken){
        return ResponseEntity.ok(new JWTResponse(refreshToken));
    }

     */
}