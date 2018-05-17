/**
 * 
 */
package com.siaam.auth.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.siaam.auth.dao.impl.UserDaoImpl;
import com.siaam.auth.utils.CommonUtils;
import com.siaam.sdm.auth.User;
import com.siaam.sdm.common.AuditSet;

/**
 * Test Case for CRUD operations on User table
 * @author kapil pruthi
 *
 */
@ContextConfiguration("classpath:sAuthTest.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoImplTest {

	@Autowired
	private UserDaoImpl userDao;
	
	private static User user;
	private static String onlineId;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user = new User();
		user.setGuid(CommonUtils.generateGUID());
		onlineId = RandomStringUtils.randomAlphanumeric(10);
		user.setOnlineId(onlineId);
		user.setRole("ROLE_ADMIN");
		user.setOnlineIdStatCd("O");
		user.setPasswordStatus("P");
		user.setPassword(CommonUtils.generateHash("passc0de"));
		user.setPasswordAlgo("algo1");
		user.setPasswordLockoutCnt(0);
		user.setEnabled(true);
		user.setHowAdded("Siaam");
		user.setFirstName("Kapil");
		user.setLastName("Pruthi");
		user.setEmail(onlineId+"@gmail.com");
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
	 * Test method for {@link com.siaam.auth.dao.impl.UserDaoImpl#createUser(com.siaam.sdm.auth.User)}.
	 */
	@Test
	public final void testBCreateUser() {
		assertEquals (this.userDao.createUser(user),1);
	}

	/**
	 * Test method for {@link com.siaam.auth.dao.impl.UserDaoImpl#readUser(java.lang.String)}.
	 */
	@Test
	public final void testCReadUser() {
		assertNotNull(this.userDao.findUserByOnlineId(onlineId));
		
	}

	/**
	 * Test method for {@link com.siaam.auth.dao.impl.UserDaoImpl#updateUser(com.siaam.sdm.auth.User)}.
	 */
	@Test
	public final void testDUpdateUserByOnlineId() {
		assertEquals (this.userDao.updateUserByOnlineId(user, onlineId),1);
	}

	/**
	 * Test method for {@link com.siaam.auth.dao.impl.UserDaoImpl#deleteUser(java.lang.String)}.
	 */
	@Test
	public final void testADeleteUserByOnlineId() {
		assertEquals (this.userDao.deleteUserByOnlineId(onlineId),1);
	}
}
