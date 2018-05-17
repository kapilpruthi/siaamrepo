/**
 * 
 */
package com.siaam.auth.handler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Test case for custom Id/Pwd Auth provider.
 * @author Kapil Pruthi
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:sAuthTest.xml")
public class SiaamAuthenticationProviderTest {

	/**
	 * Input AuthToken with Id and pwd.
	 */
	private static UsernamePasswordAuthenticationToken authToken;
	
	/**
	 * Actual Provider Impl to be tested.
	 */
	@Autowired
	private SiaamAuthenticationProvider siaamAuthProvider;

	/**
	 * set AuthToken and Mock http request and context.
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		authToken = new UsernamePasswordAuthenticationToken("kapil111", "passc0de");
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	/**
	 * setup.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.siaam.auth.handler.SiaamAuthenticationProvider#authenticate(org.springframework.security.core.Authentication)}.
	 */
	@Test
	public void testAuthenticate() {
		UsernamePasswordAuthenticationToken resp = null;
		try {
			resp = (UsernamePasswordAuthenticationToken) siaamAuthProvider.authenticate(authToken);
		} catch (Exception ex) {
			fail("Failed  because of exception " + ex);
		}
		assertNotNull(resp);
	}

}
