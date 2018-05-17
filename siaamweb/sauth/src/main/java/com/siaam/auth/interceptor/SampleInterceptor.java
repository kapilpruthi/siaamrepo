package com.siaam.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Intercepter for all controllers.
 * @author Kapil Pruthi
 */
public class SampleInterceptor implements HandlerInterceptor {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleInterceptor.class);

	/**
	 * Empty Interceptor.
	 */
	public SampleInterceptor() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(final HttpServletRequest arg0, final HttpServletResponse arg1, final Object arg2, final Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		LOGGER.debug("after completion");

	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(final HttpServletRequest arg0, final HttpServletResponse arg1, final Object arg2, final ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		LOGGER.debug("Post-handle");
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(final HttpServletRequest arg0, final HttpServletResponse arg1, final Object arg2) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.debug("Pre-handle");
		return true;
	}

}
