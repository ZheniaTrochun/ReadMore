package com.yevhenii.kpi.readmore.security;

public class CredsHolder {
    private String secret;
    private String accessToken;

    public CredsHolder() {
    }

    public CredsHolder(String secret, String accessToken) {
        this.secret = secret;
        this.accessToken = accessToken;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
