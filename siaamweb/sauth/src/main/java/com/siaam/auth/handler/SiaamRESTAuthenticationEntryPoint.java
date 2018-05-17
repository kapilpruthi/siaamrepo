package com.siaam.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Return 401 for any un-Authenticated hit to authenticated URL.
 * @author Kapil Pruthi
 */
public class SiaamRESTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
     * Return 401-Unauthorized.
     */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		 response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}
