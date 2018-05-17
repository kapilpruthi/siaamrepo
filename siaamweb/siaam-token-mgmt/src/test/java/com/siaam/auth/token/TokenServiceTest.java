package com.siaam.auth.token;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.siaam.auth.token.TokenConfig;
import com.siaam.auth.token.TokenService;
import com.siaam.auth.token.TokenVO;

/**
 * Test case for TokenServiceWrapper
 * 
 * @author Kapil Pruthi
 */
@ContextConfiguration("classpath:siaam-token-mgmt-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TokenServiceTest {

	@Autowired
	private TokenService<TokenConfig> rsaTokenService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateAndParseRSAToken() {
		// create custom claims
		Map<String, Object> customClaimMap = new HashMap<String, Object>();
		customClaimMap.put("oId", "5yHUI4tD09");
		customClaimMap.put("role", "ROLE_ADMIN");
		String encToken = rsaTokenService.createToken("19e58693-dc9d-4a14-8cfd-cad7a11053ae", "authentication",
				customClaimMap);
		TokenVO tokenVO = rsaTokenService.readToken(encToken, "authentication");
		assertEquals("19e58693-dc9d-4a14-8cfd-cad7a11053ae", tokenVO.getSubject());
	}

	@Test
	public void testCreateAndParseResetToken() {
		String encToken = rsaTokenService.createToken("19e58693-dc9d-4a14-8cfd-cad7a11053ae", "resetPwd", null);
		TokenVO tokenVO = rsaTokenService.readToken(encToken, "resetPwd");
		assertEquals("19e58693-dc9d-4a14-8cfd-cad7a11053ae", tokenVO.getSubject());
	}

}
