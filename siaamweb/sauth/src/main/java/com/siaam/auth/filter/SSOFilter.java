package com.siaam.auth.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.siaam.auth.token.TokenService;
import com.siaam.auth.token.TokenVO;
import com.siaam.auth.token.impl.rsa.RSATokenConfig;
import com.siaam.auth.utils.SiaamWebConstants;

/**
 * SSO Filter Enable Single SignOn to WebApp hits.
 * If session token (JWT) already present and valid, user will not be promoted for Auth,
 * if no session token present, route user to Login flow.
 * @author Kapil Pruthi
 */
public class SSOFilter extends AbstractPreAuthenticatedProcessingFilter {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SSOFilter.class);

	/**
	 * URls to be excluded from SSO filter.
	 */
	private String excludedURLs;

	/**
	 * List of URLs to be excluded.
	 */
	private ArrayList<String> excludeList = new ArrayList<String>();

	/**
	 * Load the URLs excluded for SSO filtering.
	 */
	public void init() {
		LOGGER.info("excludedURLs " + excludedURLs);
		StringTokenizer st = new StringTokenizer(excludedURLs, "|");
		while (st.hasMoreTokens()) {
			excludeList.add(st.nextToken());
		}
	}
	/**
     * Applying SSO to URLs that require to be pre-authenticated.
     * @param request ServletRequest
     * @param response ServletResponse
     * @param chain FilterChain
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String currUrl = ((HttpServletRequest) request).getRequestURI();
		Iterator<String> itr = excludeList.iterator();
		boolean exclude = false;
		while (itr.hasNext()) {
			if (currUrl.contains(itr.next())) {
				exclude = true;
			}
		}
		if (exclude) {
			LOGGER.info("Exclude " + currUrl + " from SSO Filter");
			chain.doFilter(request, response);
		} else {
			LOGGER.info(" ## Validate if Token present on Protected URL ## " + currUrl);
			super.doFilter(request, response, chain);
		}
	}

	/**
	 * Inject TokenAuthService.
	 */
	@Autowired
	private TokenService<RSATokenConfig> tokenService;

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		TokenVO token = getAuthentication(request);
		if (token != null && token.getClaims() != null) {
			return token.getClaims().get("oId");
		}
		return null;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		TokenVO token = getAuthentication(request);
		if (token != null && token.getSubject() != null) {
			return token.getSubject();
		}
		return null;
	}

	/**
	 * @return the excludedURLs
	 */
	public String getExcludedURLs() {
		return excludedURLs;
	}

	/**
	 * @param inExcludedURLs the excludedURLs to set
	 */
	public void setExcludedURLs(String inExcludedURLs) {
		this.excludedURLs = inExcludedURLs;
	}
	
	/**
	 * Get JWE token from header and parse to retrieve User object.
	 * @param request HttpServletRequest
	 * @return TokenVO tokenVO
	 */
	public TokenVO getAuthentication(final HttpServletRequest request) {
		final String token = request.getHeader(SiaamWebConstants.AUTH_HEADER_NAME);
		if (token != null) {
			return tokenService.readToken(token, "authentication");
		}
		return null;
	}

}
