/**
 * 
 */
package com.siaam.auth.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author kapil
 *
 */
public class CommonValidationUtilsTest {

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

	/**
	 * Test method for {@link com.siaam.auth.utils.CommonValidationUtils#isValidUserName(java.lang.String)}.
	 */
	@Test
	public final void testIsValidUserName() {
		assertTrue(CommonValidationUtils.isValidUserName("kapil"));
	}

	/**
	 * Test method for {@link com.siaam.auth.utils.CommonValidationUtils#isValidPassword(java.lang.String)}.
	 */
	@Test
	public final void testIsValidPassword() {
		assertTrue(CommonValidationUtils.isValidPassword("passc0de"));
	}

}
