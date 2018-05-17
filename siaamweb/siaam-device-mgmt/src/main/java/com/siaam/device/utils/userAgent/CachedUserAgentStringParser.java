/**
 * 
 */
package com.siaam.device.utils.userAgent;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

/**
 * Since the parsing of user agent strings is very processing costly, 
 * it's recommended to build a cache on top of the parser.
 * 
 * @author http://uadetector.sourceforge.net/usage.html
 */
public class CachedUserAgentStringParser implements UserAgentStringParser {

	/**
	 * static instance.
	 */
	private static CachedUserAgentStringParser instance = null;

	/**
	 * Exists only to defeat instantiation.
	 */
	protected CachedUserAgentStringParser() {
	}
	/**
	 * get single instance.
	 * @return instance
	 */
	public static CachedUserAgentStringParser getInstance() {
		if (instance == null) {
			instance = new CachedUserAgentStringParser();
		}
		return instance;
	}
	
	/**
	 * Get UserAgentStringParser.
	 */
	private final UserAgentStringParser parser = UADetectorServiceFactory
			.getCachingAndUpdatingParser();
	
	/**
	 * Cache the UA Db for better performance.
	 */
	private final Cache<String, ReadableUserAgent> cache = CacheBuilder.newBuilder()
			.maximumSize(100)
			.expireAfterWrite(2, TimeUnit.HOURS)
			.build();

	/* (non-Javadoc)
	 * @see net.sf.uadetector.UserAgentStringParser#getDataVersion()
	 */
	@Override
	public String getDataVersion() {
		return parser.getDataVersion();
	}

	/* (non-Javadoc)
	 * @see net.sf.uadetector.UserAgentStringParser#parse(java.lang.String)
	 */
	@Override
	public ReadableUserAgent parse(String userAgentString) {
		ReadableUserAgent result = cache.getIfPresent(userAgentString);
		if (result == null) {
			result = parser.parse(userAgentString);
			cache.put(userAgentString, result);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see net.sf.uadetector.UserAgentStringParser#shutdown()
	 */
	@Override
	public void shutdown() {
		parser.shutdown();
	}

}
