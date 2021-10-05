package vRealizeServiceBroker.catalog.model;

import java.util.Objects;

public class BearerToken {
    private String tokenType;
    private String token;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BearerToken that = (BearerToken) o;
        return Objects.equals(tokenType, that.tokenType) && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenType, token);
    }

    @Override
    public String toString() {
        return "BearerToken{" +
                "tokenType='" + tokenType + '\'' +
                ", token='" + token + '\'' +
                '}';
    }


}
