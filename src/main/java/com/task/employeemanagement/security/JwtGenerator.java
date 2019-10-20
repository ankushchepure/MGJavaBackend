package com.task.employeemanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.task.employeemanagement.entity.Manager;

@Component
public class JwtGenerator {

	@Value("${jwt.secret.key}")
	private String JWTAuthKey;

	public String generate(Manager manager) {

		Claims claims = Jwts.claims().setSubject(String.valueOf(manager.getId()));
		System.out.print(manager.getId());
		return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(SignatureAlgorithm.HS512, JWTAuthKey).compact();
	}

}
