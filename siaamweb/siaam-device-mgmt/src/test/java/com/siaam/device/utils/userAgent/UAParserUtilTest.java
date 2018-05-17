/**
 * 
 */
package com.siaam.device.utils.userAgent;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.siaam.sdm.device.Device;

/**
 * Junit for UserAgent Parsing APIs.
 * 
 * @author kapil pruthi
 */
public class UAParserUtilTest {

	/**
	 * user agent.
	 */
	private String userAgent;
	
	/**
	 * Device.
	 */
	private Device device;

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
		//userAgent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0_1 like Mac OS X; fr-fr) AppleWebKit/532.9";
		userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:46.0) Gecko/20100101 Firefox/46.0"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
		device = new Device();
		device.setDeviceId(UUID.randomUUID().toString());
	}

	/**
	 * Test method for
	 * {@link com.siaam.device.utils.userAgent.UAParserUtil#parseUAAndUpdateDevice(com.siaam.sdm.device.Device, java.lang.String)}
	 * .
	 */
	@Test
	public void testParseUAAndUpdateDevice() {
		UAParserUtil.parseUAAndUpdateDevice(device, userAgent);
		printDevice(device);
		assertTrue(device.getCategory() != null);
	}

	/**
	 * Print the device.
	 * @param device Device
	 */
	private void printDevice(Device device) {
		System.out.println("deviceId " + device.getDeviceId());
		System.out.println("deviceCategory " + device.getCategory());
		System.out.println("Family " + device.getFamily());
		System.out.println("OS " + device.getOs());
		System.out.println("TypeName " + device.getTypeName());
		System.out.println("Version " + device.getVersion());
	}

}
