package com.siaam.auth.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siaam.auth.utils.WebUtils;

/**
 * Servlet Filter implementation class SessionFilter
 * @author kapil pruthi
 */
public class SessionFilter implements Filter {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);

	/**
	 * Entry Point URls. Move to config.
	 */
	private String entryPointURLs = "login|loginws|j_spring_security_check|resetPwd|register|resetId";

	/**
	 * list to hold entry point urls.
	 */
	private ArrayList<String> entryPoints;

	/**
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * (non-Javadoc)
	 * @param request ServletRequest
	 * @param response ServletResponse
	 * @param chain FilterChain
	 * @throws IOException Exception
	 * @throws ServletException Exception
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = ((HttpServletRequest) request).getRequestURI();

		Iterator<String> itr = entryPoints.iterator();
		boolean isEntryPoint = false;
		while (itr.hasNext()) {
			if (url.contains(itr.next())) {
				isEntryPoint = true;
			}
		}

		if (isEntryPoint) {
			// Cleaning prior cookies for entry points.
			WebUtils.cleanAuthSessionInfo((HttpServletResponse) response);
			LOGGER.info("Must Invalidate Session as Entry point URL " + url);
			HttpSession session = req.getSession(false);
			if (session != null) {
				session.invalidate();
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * (non-Javadoc)
	 * @param fConfig FilterConfig
	 * @throws ServletException Exception
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOGGER.info("Entry Points " + entryPointURLs);
		entryPoints = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(entryPointURLs, "|");
		while (st.hasMoreTokens()) {
			entryPoints.add(st.nextToken());
		}
	}
}
