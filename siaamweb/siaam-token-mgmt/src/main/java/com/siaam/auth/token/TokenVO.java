package com.siaam.auth.token;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic Token Value Object.
 * Custom implementations should extend tis base impl.
 * @author Kapil Pruthi
 */
public class TokenVO {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenVO.class);
	/**
	 * GUID.
	 */
	private String subject;
	/**
	 * Issuer.
	 */
	private String issuer;
	/**
	 * Expiration Time.
	 */
	private Date expTime;
	/**
	 * Custom Claims Map
	 */
	private Map<String, Object> claims;

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the issuer
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 * @param inIssuer
	 *            the issuer to set
	 */
	public void setIssuer(final String inIssuer) {
		this.issuer = inIssuer;
	}

	/**
	 * @return the expTime
	 */
	public Date getExpTime() {
		return expTime;
	}

	/**
	 * @param inExpTime
	 *            the expTime to set
	 */
	public void setExpTime(final Date inExpTime) {
		this.expTime = inExpTime;
	}

	/**
	 * @return the claims
	 */
	public Map<String, Object> getClaims() {
		return claims;
	}

	/**
	 * @param claims
	 *            the claims to set
	 */
	public void setClaims(Map<String, Object> claims) {
		this.claims = claims;
	}

	/**
	 * Print the VO fields.
	 */
	public void printToken() {
		StringBuilder sb = new StringBuilder();
		sb.append(" subject " + subject);
		sb.append(" issuer " + issuer);
		sb.append(" expTime " + expTime);
		LOGGER.info(sb.toString());
	}

}
