package ru.projectosnova.identityprovider.jwt;

public class JWTTokenPairResponse {

    private String accessToken;
    private String refreshToken;

    public JWTTokenPairResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
