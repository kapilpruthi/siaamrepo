package com.siaam.auth.token.impl.rsa;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.siaam.auth.token.TokenHandler;
import com.siaam.auth.token.TokenVO;

/**
 * RSATokenHandler is the RSA implementation for TokenHandler and is primarily
 * responsible to create/parse JWE tokens using RSA Asymmetric Encryption
 * algorithm.
 * 
 * @author Kapil Pruthi
 */
public final class RSATokenHandler extends TokenHandler<RSATokenConfig> {
   /**
	 * Parse JWE Token using RSA algo.
	 * @param token Encrypted Token
	 * @param config RSATokenConfig
	 * @return TokenVO TokenVO
	 */
	public TokenVO parseToken(final String token, final RSATokenConfig config) {
		if (token != null) {
			// Parse into JWE object again...
			EncryptedJWT jwt;
			try {
				jwt = EncryptedJWT.parse(token);
				// create a decryptor with the specified private RSA key
				RSADecrypter decrypter = new RSADecrypter(config.getPrivateRsaKey());

				// do the decryption
				jwt.decrypt(decrypter);
				TokenVO tokenVO = new TokenVO();
				tokenVO.setSubject(jwt.getJWTClaimsSet().getSubject());
				tokenVO.setIssuer(jwt.getJWTClaimsSet().getIssuer());
				tokenVO.setExpTime(jwt.getJWTClaimsSet().getExpirationTime());
				return tokenVO;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

    /**
	 * Create a JWE Token using RSA algo.
	 * @param subject Guid
	 * @param customClaimMap Custom Attributes in JWE token
	 * @param config RSATokenConfig
	 * @return Encrypted token
	 */
	public String createToken(final String subject, final Map<String, Object> customClaimMap, final RSATokenConfig config) {
		// Create Claims/Body of JWE Token
		JWTClaimsSet jwtClaims = new JWTClaimsSet();
		Date date = new Date();
		jwtClaims.setIssueTime(date);
		// Max exp time can be 1 hr from issue time
		jwtClaims.setExpirationTime(new Date(date.getTime() + config.getTokenExpiry()));
		jwtClaims.setJWTID(UUID.randomUUID().toString());
		jwtClaims.setIssuer(config.getIssuer());
		jwtClaims.setSubject(subject);

		// Add custom attributes specific to user.
		jwtClaims.setCustomClaims(customClaimMap);

		// Request JWT encrypted with RSA-OAEP and 128-bit AES/GCM
		JWEHeader header = new JWEHeader(config.getAlgo(), config.getEnc());

		// Create the encrypted JWT object
		EncryptedJWT token = new EncryptedJWT(header, jwtClaims);

		// Create an encryptor with the specified public RSA key
		RSAEncrypter encrypter = new RSAEncrypter(config.getPublicRsaKey());

		try {
			// Do the actual encryption
			token.encrypt(encrypter);
			// Serialize to compact JOSE form...
			return token.serialize();
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
