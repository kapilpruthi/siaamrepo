package com.siaam.auth.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.siaam.auth.token.TokenService;
import com.siaam.auth.utils.SiaamWebConstants;
import com.siaam.sdm.auth.User;

/**
 * @author Kapil Pruthi
 */
public abstract class AbstractSiaamAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	/**
	 * Inject TokenAuthService.
	 */
	@Autowired
	private TokenService tokenService;

	/**
	 * Create JWE token and add to the header.
	 * @param response HttpServletResponse
	 * @param user User
	 * @return String
	 */
	public String addAuthentication(final HttpServletResponse response, final User user) {
		//create custom claims
		Map<String, Object> customClaimMap = new HashMap<String, Object>();
		customClaimMap.put("oId", user.getOnlineId());
		customClaimMap.put("role", user.getRole());
		//Create Token
		String token = tokenService.createToken(user.getGuid(), "authentication", customClaimMap);
		//add token to Http response header
		response.addHeader(SiaamWebConstants.AUTH_HEADER_NAME, token);
		return token;
	}
}
