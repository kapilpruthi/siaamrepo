/**
 * 
 */
package com.siaam.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.siaam.auth.utils.SiaamWebConstants;
import com.siaam.auth.utils.WebUtils;

/**
 * Custom LogOut handler, clear App specific headers & other attributes before session clearing.
 * @author kapil pruthi
 */
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutSuccessHandler.class);

	// Just for setting the default target URL
	/**
	 * @param defaultTargetURL default target url.
	 */
	public LogoutSuccessHandler(String defaultTargetURL) {
		this.setDefaultTargetUrl(defaultTargetURL);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.
	 * SimpleUrlLogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// Clear Token from header
		response.setHeader(SiaamWebConstants.AUTH_HEADER_NAME, "DUMMY");
		WebUtils.cleanAuthSessionInfo(response);
		LOGGER.info("Invoking logout for " + authentication.getName() + " source " + (String) request.getParameter("source"));
		//use some AppId or ClientId from each client than passing params in URLs.
		if ("mApp".equalsIgnoreCase(request.getParameter("source"))) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().flush();
		} else {
			super.onLogoutSuccess(request, response, authentication);
		}

	}
}
