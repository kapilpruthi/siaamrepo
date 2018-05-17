/**
 * 
 */
package com.siaam.auth.token.impl.rsa;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.siaam.auth.token.TokenConfig;

/**
 * RSA Token Config placeholder for token expiration & public/private key pair.
 * When initialized RSA private and public keys are derived.
 * @author Kapil Pruthi
 */
public class RSATokenConfig extends TokenConfig {
	/**
	 * Constructor.
	 * @param tokenExpiry long
	 * @param modulus String
	 * @param publicExponent String
	 * @param privateExponent String
	 * @throws InvalidKeySpecException Exception
	 * @throws NoSuchAlgorithmException Exception
	 */
	public RSATokenConfig(long tokenExpiry, String modulus, String publicExponent, String privateExponent)
			throws InvalidKeySpecException, NoSuchAlgorithmException {
		setTokenExpiry(tokenExpiry);
		// this.modulus = modulus;
		BigInteger mod = new BigInteger(modulus);
		BigInteger privexponent = new BigInteger(privateExponent);
		BigInteger publicexponent = new BigInteger(publicExponent);
		RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(mod, privexponent);
		RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privateKeySpec.getModulus(), publicexponent);
		// PKI- RSA Public/Private key pair.
		KeyFactory kf = KeyFactory.getInstance("RSA");
		// generate (and retrieve) RSA Keys from the KeyFactory using Keys
		setPrivateRsaKey((RSAPrivateKey) kf.generatePrivate(privateKeySpec));
		setPublicRsaKey((RSAPublicKey) kf.generatePublic(publicKeySpec));
		setIssuer("Siaam");
		setAlgo(JWEAlgorithm.RSA_OAEP);
		setEnc(EncryptionMethod.A128GCM);
	}

	/**
	 * privateRsaKey.
	 */
	private RSAPrivateKey privateRsaKey;
	/**
	 * publicRsaKey.
	 */
	private RSAPublicKey publicRsaKey;

	/**
	 * @return the privateRsaKey
	 */
	public RSAPrivateKey getPrivateRsaKey() {
		return privateRsaKey;
	}

	/**
	 * @param privateRsaKey
	 *            the privateRsaKey to set
	 */
	public void setPrivateRsaKey(RSAPrivateKey privateRsaKey) {
		this.privateRsaKey = privateRsaKey;
	}

	/**
	 * @return the publicRsaKey
	 */
	public RSAPublicKey getPublicRsaKey() {
		return publicRsaKey;
	}

	/**
	 * @param publicRsaKey
	 *            the publicRsaKey to set
	 */
	public void setPublicRsaKey(RSAPublicKey publicRsaKey) {
		this.publicRsaKey = publicRsaKey;
	}
}
