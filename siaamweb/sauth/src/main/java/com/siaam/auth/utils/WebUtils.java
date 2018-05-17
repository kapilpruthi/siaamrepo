/**
 * 
 */
package com.siaam.auth.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kapil pruthi
 *
 */
public final class WebUtils {

	/**
	 * Utility class should not have public constructor.
	 */
	private WebUtils() {
		
	}
	/**
	 * Get Current URL with Domain, Port and Context.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return current URL
	 */
	public static String getCurrentURL(final HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		return url.substring(0, url.length() - uri.length()) + ctx;
	}

	/**
	 * Erase Cookie
	 * 
	 * @param strCookieName
	 *            Name of the cookie
	 * @param strPath
	 *            path
	 * @return Cookie
	 */
	public static Cookie eraseCookie(String strCookieName, String strPath) {
		Cookie cookie = new Cookie(strCookieName, "");
		cookie.setMaxAge(0);
		cookie.setPath(strPath);

		return cookie;
	}
	
	/**
	 * Clean Auth Session Info from Header and Cookie
	 * @param response HttpServletResponse
	 */
	public static void cleanAuthSessionInfo(HttpServletResponse response) {
		// Clear Token from header
		response.setHeader(SiaamWebConstants.AUTH_HEADER_NAME, "DUMMY");
		eraseCookie(SiaamWebConstants.AUTH_HEADER_NAME, "/");
	}
}
