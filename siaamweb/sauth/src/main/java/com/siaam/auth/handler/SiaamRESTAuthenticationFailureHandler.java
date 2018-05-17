package com.siaam.auth.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * <tt>AuthenticationFailureHandler</tt> which add 401 to HttpServletResponse
 * when the <tt>onAuthenticationFailure</tt> method is called.
 * @author Kapil Pruthi
 */
public class SiaamRESTAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SiaamRESTAuthenticationFailureHandler.class);

	/**
     * Add 401-Unauthorized to HttpServletResponse
     * the target view.
     */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Credentials");
		LOGGER.info(" Returning 401 " + exception.getMessage());
		PrintWriter writer = response.getWriter();
		writer.write(exception.getMessage());
		writer.flush();
	}

}
