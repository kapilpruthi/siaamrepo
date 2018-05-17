/**
 * 
 */
package com.siaam.auth.dao.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.siaam.sdm.auth.User;
import com.siaam.sdm.common.AuditSet;

/**
 * @author kapil pruthi
 *
 */
@ContextConfiguration("classpath:sAuthTest.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserCredentialsDaoImplTest {

	@Autowired
	private UserCredentialsDaoImpl userCredentialsDao;
	
	private static User user;
	private static String onlineId;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user = new User();
		user.setGuid("ece82ab6-844b-4b45-8762-b0368c6a959d");
		onlineId = "pruthik_21";
		user.setOnlineId(onlineId);
		user.setRole("ROLE_ADMIN");
		user.setOnlineIdStatCd("O");
		user.setPasswordStatus("R");
		user.setPassword("passc0de");
		user.setPasswordAlgo("algo1");
		user.setPasswordLockoutCnt(0);
		user.setEnabled(true);
		AuditSet audit = new AuditSet();
		audit.setCreatedBy("SiaamApp");
		audit.setUpdatedBy("SiaamApp");
		audit.setSiteId("TX");
		user.setAuditgroup(audit);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}


	/**
	 * Test method for {@link com.siaam.auth.dao.impl.UserCredentialsDaoImpl#updatePwdStatus(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testUpdatePwdStatus() {
		assertEquals (this.userCredentialsDao.updatePwdStatus(user.getGuid(),user.getPasswordStatus()),1);
	}

	/**
	 * Test method for {@link com.siaam.auth.dao.impl.UserCredentialsDaoImpl#updatePwd(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testUpdatePwd() {
		assertEquals (this.userCredentialsDao.updatePwd(user.getGuid(),user.getPassword()),1); 
	}

	/**
	 * Test method for {@link com.siaam.auth.dao.impl.UserCredentialsDaoImpl#updateOnlineId(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testUpdateOnlineId() {
		assertEquals (this.userCredentialsDao.updateOnlineId(user.getGuid(), user.getOnlineId()+"a"),1);
	}

	/**
	 * Test method for {@link com.siaam.auth.dao.impl.UserCredentialsDaoImpl#updateOnlineIdStatCd(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testUpdateOnlineIdStatCd() {
		assertEquals (this.userCredentialsDao.updateOnlineIdStatCd(user.getGuid(), user.getOnlineIdStatCd()),1);
	}

}
