package com.siaam.auth.oauth;

import java.util.Collections;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * @author Kapil Pruthi
 */
public class SiaamTokenEnhancer implements TokenEnhancer {

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.oauth2.provider.token.TokenEnhancer#enhance(
	 * org.springframework.security.oauth2.common.OAuth2AccessToken,
	 * org.springframework.security.oauth2.provider.OAuth2Authentication)
	 */
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
		result.setAdditionalInformation(
				Collections.singletonMap("client_id", (Object) authentication.getOAuth2Request().getClientId()));
		return result;
	}

}