
package com.siaam.device.utils.userAgent;

import com.siaam.sdm.device.Device;

import net.sf.uadetector.ReadableUserAgent;

/**
 * UserAgent Parsing Utility.
 * 
 * @author kapil pruthi
 */
public final class UAParserUtil {

	/**
	 * private constructor.
	 */
	private UAParserUtil() {
	}

	/**
	 * Parse the supplied UserAgent and populate Device Obj.
	 * 
	 * @param device
	 *            Device
	 * @param userAgent
	 *            UserAgent String
	 */
	public static void parseUAAndUpdateDevice(Device device, String userAgent) {
		// Get an UserAgentStringParser and analyze the requesting client
		CachedUserAgentStringParser parser = CachedUserAgentStringParser.getInstance();
		ReadableUserAgent agent = parser.parse(userAgent);
		device.setCategory(agent.getDeviceCategory().getName());
		device.setFamily(agent.getFamily().getName());
		device.setOs(agent.getOperatingSystem().getFamily().getName() + " "
				+ agent.getOperatingSystem().getVersionNumber().toVersionString());
		device.setTypeName(agent.getTypeName());
		device.setVersion(agent.getVersionNumber().toVersionString());
		device.setUserAgent(userAgent);
	}
}
