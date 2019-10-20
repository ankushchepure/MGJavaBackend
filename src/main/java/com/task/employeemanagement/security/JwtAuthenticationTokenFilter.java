package com.task.employeemanagement.security;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import com.task.employeemanagement.entity.Manager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	public JwtAuthenticationTokenFilter() {
		super("/employee/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest p_httpServletRequest,
			HttpServletResponse p_httpServletResponse) throws AuthenticationException, IOException, ServletException {

		String l_header = p_httpServletRequest.getHeader("Authorization");
		if (l_header == null || !l_header.startsWith("Token ")) {
			throw new RuntimeException("JWT Token is missing");
		}

		String l_authenticationToken = l_header.substring(6);

		JwtAuthenticationToken token = new JwtAuthenticationToken(l_authenticationToken,
				p_httpServletRequest.getRequestURI(), p_httpServletRequest.getMethod());
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest l_request, HttpServletResponse l_response,
			FilterChain l_chain, Authentication l_authResult) throws IOException, ServletException {
		super.successfulAuthentication(l_request, l_response, l_chain, l_authResult);

		MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest((HttpServletRequest) l_request);

		String l_header = multiReadRequest.getHeader("Authorization");
		String l_containtType = multiReadRequest.getHeader("Content-Type");
		String l_authenticationToken = l_header.substring(6);
		Manager l_jwtUser = new JwtValidator().validateJWTAuthKey(l_authenticationToken, "employeeMaster");
		if ("POST".equalsIgnoreCase(multiReadRequest.getMethod())) {
			String id = IOUtils.toString(multiReadRequest.getReader());
			JSONParser parser = new JSONParser();
			JSONObject json;
			try {
				json = (JSONObject) parser.parse(id);
				Long userid = (Long) json.get("managerid");
				if (l_jwtUser.getId() == userid) {
					l_chain.doFilter(multiReadRequest, l_response);
				} else {
					l_response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
				}
			} catch (ParseException e) {
				l_response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
				e.printStackTrace();
			} catch (Exception es) {
				l_response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
				es.printStackTrace();
			}

		} else if ("GET".equalsIgnoreCase(multiReadRequest.getMethod())) {
			try {
			String id = multiReadRequest.getRequest().getParameter("managerid");
			if (l_jwtUser.getId() == Integer.parseInt(id)) {
				l_chain.doFilter(multiReadRequest, l_response);
			} else {
				l_response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
			}
			}catch(Exception e) {
				l_response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
			}

		}
	}
}
