package com.siaam.auth.token;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;

/**
 * Abstract JWT Token MetaData/Config
 * @author Kapil Pruthi
 */
public abstract class TokenConfig {
	/**
	 * TOKEN_EXP_LENGTH.
	 */
	private long tokenExpiry;
	/**
	 * issuer.
	 */
	private String issuer;
	/**
	 * issuer.
	 */
	private String tokenId;
	/**
	 * JWEAlgorithm.
	 */
	private JWEAlgorithm algo;
	/**
	 * EncryptionMethod
	 */
	private EncryptionMethod enc;
	/**
	 * @return the tokenExpiry
	 */
	public long getTokenExpiry() {
		return tokenExpiry;
	}

	/**
	 * @param tokenExpiry
	 *            the tokenExpiry to set
	 */
	public void setTokenExpiry(long tokenExpiry) {
		this.tokenExpiry = tokenExpiry;
	}

	/**
	 * @return the issuer
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 * @param issuer
	 *            the issuer to set
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 * @return the tokenId
	 */
	public String getTokenId() {
		return tokenId;
	}

	/**
	 * @param tokenId
	 *            the tokenId to set
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	/**
	 * @return the algo
	 */
	public JWEAlgorithm getAlgo() {
		return algo;
	}

	/**
	 * @param algo
	 *            the algo to set
	 */
	public void setAlgo(JWEAlgorithm algo) {
		this.algo = algo;
	}

	/**
	 * @return the enc
	 */
	public EncryptionMethod getEnc() {
		return enc;
	}

	/**
	 * @param enc
	 *            the enc to set
	 */
	public void setEnc(EncryptionMethod enc) {
		this.enc = enc;
	}
}
