package com.task.employeemanagement.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	private String JWTAuthKey="employee";
	
    public String generate() {
    	
        Claims claims = Jwts.claims()
                .setSubject("test");
        claims.put("userId", "1");
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, JWTAuthKey)
                .setExpiration(new Date(System.currentTimeMillis()+86400000))
                .compact();
    }

}
