package com.siaam.device.dao.impl;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.siaam.sdm.common.AuditSet;
import com.siaam.sdm.device.Device;
import com.siaam.sdm.device.UserDevice;

/**
 * Test Case for CRUD operations on Device table
 * 
 * @author kapil pruthi
 *
 */
@ContextConfiguration("classpath:siaam-device-mgmt-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDeviceDaoImplTest {

	/**
	 * DeviceDaoImpl Obj.
	 */
	@Autowired
	private DeviceDaoImpl deviceDao;

	/**
	 * UserDeviceDaoImpl Obj.
	 */
	@Autowired
	private UserDeviceDaoImpl userDeviceDao;

	/**
	 * Device Obj.
	 */
	private static Device device;
	/**
	 * UserDevice Obj.
	 */
	private static UserDevice userDevice;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		device = new Device();
		device.setDeviceId(UUID.randomUUID().toString());
		device.setIp("192.0.0.1");
		device.setLocation("US");
		device.setCategory("Personal computer");
		device.setFamily("FIREFOX");
		device.setOs("OS X 10.11");
		device.setTypeName("Browser");
		device.setVersion("46.0Accept");
		device.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:46.0) Gecko/20100101 Firefox/46.0"+
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		AuditSet audit = new AuditSet();
		audit.setCreatedBy("SiaamApp");
		audit.setUpdatedBy("SiaamApp");
		audit.setSiteId("TX");
		device.setAuditgroup(audit);

		userDevice = new UserDevice();
		userDevice.setGuid("0910b06b-1d48-4718-b790-580eeb24a25f");

		userDevice.setAuditgroup(audit);
	}

	/**
	 * Test API for Creating User Device(s).
	 */
	@Test
	public final void testACreateUserDevice() {
		int i = 0;
		// add 3 devices for the user.
		while (i < 4) {
			// First create a device entry in device table so device_id FK is
			// satisfied
			device.setDeviceId(UUID.randomUUID().toString());
			this.deviceDao.createDevice(device);
			// Now add the user & device mapping.
			userDevice.setDeviceId(device.getDeviceId());
			i = i + this.userDeviceDao.createUserDevice(userDevice);
		}
		assertTrue(i >= 3);
	}

	/**
	 * Test API for extracting all the User Device(s).
	 */
	@Test
	public final void testBFindAllDevicesOfUser() {
		assertTrue(this.userDeviceDao.findAllDevicesOfUser(userDevice.getGuid()).size() >= 3);
	}

	/**
	 * Test API for deleting all the User Device(s).
	 */
	
	@Test
	public final void testCDeleteDevice() {
		assertTrue(this.userDeviceDao.deleteAllDevicesOfUser(userDevice.getGuid()) >= 1);
	}

}
