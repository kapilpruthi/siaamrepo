package com.siaam.auth.token;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Token Service Wrapper to create/read tokens for any Implementation and operation. 
 * Multiple TokenConfigs based on Operation are injected to this bean.
 * 
 * @author Kapil Pruthi
 * @param <T> TokenConfig
 */
public class TokenService<T extends TokenConfig> {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

	/**
	 * Map of TokenConfigs.
	 */
	private Map<String, T> tokenConfigMap;

	/**
	 * @param tokenConfigMap the tokenConfigMap to set
	 */
	public void setTokenConfigMap(Map<String, T> tokenConfigMap) {
		this.tokenConfigMap = tokenConfigMap;
	}

	/**
	 * @return the tokenConfigMap
	 */
	public Map<String, T> getTokenConfigMap() {
		return tokenConfigMap;
	}
	
	
	/**
	 * TokenHandler
	 */
	private TokenHandler<T> tokenHandler;

	/**
	 * @return the tokenHandler
	 */
	public TokenHandler<T> getTokenHandler() {
		return tokenHandler;
	}

	/**
	 * @param tokenHandler the tokenHandler to set
	 */
	public void setTokenHandler(TokenHandler<T> tokenHandler) {
		this.tokenHandler = tokenHandler;
	}

	/**
	 * Create a token for a particular GUID/Flow.
	 * 
	 * @param subject
	 *            guid
	 * @param operation
	 *            flow name
	 * @param customClaimMap
	 *            customAttributes
	 * @return Token
	 */
	public String createToken(String subject, String operation, Map<String, Object> customClaimMap) {
		T tokenMD = (T) getTokenConfigMap().get(operation);
		return tokenHandler.createToken(subject, customClaimMap, tokenMD);
	}

	/**
	 * Parse the JWT token for a particular flow.
	 * 
	 * @param token
	 *            encrypted token
	 * @param operation
	 *            flow name
	 * @return TokenVO
	 */
	public TokenVO readToken(String token, String operation) {
		T tokenMD = (T) getTokenConfigMap().get(operation);
		if (token != null) {
			TokenVO tokenVO = tokenHandler.parseToken(token, tokenMD);
			if (tokenVO != null) {
				if (tokenVO.getExpTime().before(new Date())) {
					LOGGER.info("Expired Token for GUID " + tokenVO.getSubject());
					return null;
				}
				return tokenVO;
			}
		}
		return null;
	}
}
