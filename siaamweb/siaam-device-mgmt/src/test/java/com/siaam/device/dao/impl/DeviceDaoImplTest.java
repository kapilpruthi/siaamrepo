package com.siaam.device.dao.impl;

import static org.junit.Assert.assertEquals;

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

/**
 * Test Case for CRUD operations on Device table
 * 
 * @author kapil pruthi
 *
 */
@ContextConfiguration("classpath:siaam-device-mgmt-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeviceDaoImplTest {

	@Autowired
	private DeviceDaoImpl deviceDao;

	private static Device device;
	private static String deviceId;

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
	}

	@Test
	public final void testACreateDevice() {
		assertEquals(this.deviceDao.createDevice(device), 1);
	}

	@Test
	public final void testBFindDevice() {
		assertEquals(this.deviceDao.findDevice(device.getDeviceId()).getDeviceId(), device.getDeviceId());
	}

	@Test
	public final void testCUpdateDevice() {
		deviceId = device.getDeviceId();
		device.setDeviceId(UUID.randomUUID().toString());
		assertEquals(this.deviceDao.updateDevice(deviceId, device), 1);
	}

	@Test
	public final void testDDeleteDevice() {
		assertEquals(this.deviceDao.deleteDevice(device.getDeviceId()), 1);
	}

}
