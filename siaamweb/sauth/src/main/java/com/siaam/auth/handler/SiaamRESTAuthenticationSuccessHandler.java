package com.siaam.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.siaam.sdm.auth.User;

/**
 * Extend & Modify <tt>SimpleUrlAuthenticationSuccessHandler</tt> to not redirect to any URL
 * <p>
 * This primarily used for REST Services when 200 is returned for Success scenario instead 301 redirect.
 * On success, the handler adds a SSO Token to response header
 * @author Kapil Pruthi
 */
public class SiaamRESTAuthenticationSuccessHandler extends AbstractSiaamAuthenticationSuccessHandler {

	/**
	 * HttpSessionRequestCache.
	 */
	private RequestCache requestCache = new HttpSessionRequestCache();
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SiaamRESTAuthenticationSuccessHandler.class);

	/**
     * Calls the parent class {@code handle()} method to forward or redirect to the target URL, and
     * then calls {@code clearAuthenticationAttributes()} to remove any leftover session data.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param authentication Authentication
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	//Retrieve User Object from Request Attribute for Token creation
    	User user = (User) request.getAttribute("user");
    	LOGGER.info("Adding SSO Token for guid " + user.getGuid() + " online id " + user.getOnlineId());
    	addAuthentication(response, user);

    	final SavedRequest savedRequest = requestCache.getRequest(request, response);
    	if (savedRequest == null) {
            clearAuthenticationAttributes(request);
            return;
        }
        final String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl()
				|| (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
		    requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            return;
        }

        clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
        // final String targetUrl = savedRequest.getRedirectUrl();
        // logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        // getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    /**
     * Setter for RequestCache.
     * @param inRequestCache RequestCache
     */
    public void setRequestCache(final RequestCache inRequestCache) {
        this.requestCache = inRequestCache;
    }
}
