package com.task.employeemanagement.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken{

    private String token;
	private String url;
	private String method;

    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public JwtAuthenticationToken(String p_token, String RequestedURL, String method) {
        super(null, null);
        this.token = p_token;
        this.url=RequestedURL;
        this.method=method;
    }
}
