package ru.ekuchin.identityprovider.jwt;

public class JWTAuthRequest {
    private String username;
    private String password;

    public JWTAuthRequest() {}

    public JWTAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}