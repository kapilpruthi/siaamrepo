/**
 * 
 */
package com.siaam.auth.token;

import java.util.Map;

/**
 * Abstract Token Handler for Token creation/parsing APIs.
 * Subclasses will provide implementations based on TokenConfig. 
 * @author Kapil Pruthi
 * @param <T> Implementation of TokenConfig
 */
public abstract class TokenHandler<T extends TokenConfig> {

	/**
	 * Map of TokenConfigs.
	 */
	private Map<String, ? extends TokenConfig> tokenConfigMap;

	/**
	 * @return the tokenConfigMap
	 */
	public Map<String, ? extends TokenConfig> getTokenConfigMap() {
		return tokenConfigMap;
	}

	/**
	 * @param tokenConfigMap the tokenConfigMap to set
	 */
	public void setTokenConfigMap(Map<String, ? extends TokenConfig> tokenConfigMap) {
		this.tokenConfigMap = tokenConfigMap;
	}

	/**
	 * Standard API for token parsing.
	 * @param token encrypted token string.
	 * @param config TokenConfig
	 * @return TokenVO
	 */
	public abstract TokenVO parseToken(String token, T config);

	/**
	 * Standard API for token creation.
	 * @param subject userId
	 * @param customClaimMap claims
	 * @param config TokenConfig
	 * @return token string
	 */
	public abstract String createToken(String subject, Map<String, Object> customClaimMap, T config);

}