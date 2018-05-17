package com.siaam.auth.token.impl.rsa;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.siaam.auth.token.TokenVO;
import com.siaam.auth.token.impl.rsa.RSATokenConfig;
import com.siaam.auth.token.impl.rsa.RSATokenHandler;

/**
 * Test case for RSA TokenHandler
 * 
 * @author kapil pruthi
 */
@ContextConfiguration("classpath:siaam-token-mgmt-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RSATokenHandlerTest {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RSATokenHandlerTest.class);

	@Autowired
	private RSATokenConfig resetPwdTokenConfig;

	@Autowired
	private RSATokenHandler tokenHandler;
	/**
	 * Test both creation & parsing of JSON token.
	 */
	@Test
	public final void testCreateAndParseToken() {
		// subject - guid
		String subject = "19e58693-dc9d-4a14-8cfd-cad7a11053ae";
		// create custom claims
		Map<String, Object> customClaimMap = new HashMap<String, Object>();
		customClaimMap.put("oId", "5yHUI4tD09");
		customClaimMap.put("role", "ROLE_ADMIN");
		String encToken = tokenHandler.createToken(subject, customClaimMap, resetPwdTokenConfig);
		LOGGER.info("JWE Token### " + encToken);
		TokenVO tokenVO = tokenHandler.parseToken(encToken, resetPwdTokenConfig);
		tokenVO.printToken();
		assertNotNull(tokenVO);
	}
}
