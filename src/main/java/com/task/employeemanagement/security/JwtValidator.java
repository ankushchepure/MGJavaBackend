package com.task.employeemanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import com.task.employeemanagement.entity.Manager;


@Component
public class JwtValidator {
	
	public Manager validateJWTAuthKey(String token,String JWTAuthKey) {

		Manager manager = null;
		try {
			Claims body = Jwts.parser().setSigningKey(JWTAuthKey).parseClaimsJws(token).getBody();
			manager = new Manager();
			manager.setId(Integer.parseInt(body.getSubject()));
		} catch (Exception e) {
			System.out.println(e);
		}

		return manager;
	}
}
