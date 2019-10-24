package com.ga.entity;

import org.springframework.stereotype.Component;


public class Response {
    private String token;
    private String username;

    public Response() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
