/**
 * 
 */
package com.siaam.auth.utils;

/**
 * @author kapil pruthi
 */
public final class SiaamWebConstants {

	/**
	 * Private Constructor for class containing constants.
	 */
	private SiaamWebConstants() {
		
	}
	/**
	 * REGEX_OID
	 */
	public static final String REGEX_OID = "^[a-z0-9_-]{5,12}$";
	/**
	 * REGEX_PWD
	 */
	public static final String REGEX_PWD = "^[a-z0-9_-]{6,15}$";
	/**
	 * REGEX_FIRST_NAME
	 */
	public static final String REGEX_FIRST_NAME = "([a-zA-Z]{3,30}\\s*)+";
	/**
	 * REGEX_LAST_NAME
	 */
	public static final String REGEX_LAST_NAME = "[a-zA-Z]{3,30}";
	/**
	 * REGEX_GUID
	 */
	public static final String REGEX_GUID = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
	
	/**
	 * ROLE_USER
	 */
	public static final String ROLE_USER = "ROLE_USER";
	/**
	 * ROLE_ADMIN
	 */
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	/**
	 * ROLE_CLIENT
	 */
	public static final String ROLE_CLIENT = "ROLE_CLIENT";
	
	/**
	 * PWD_STATUS_PERM
	 */
	public static final String PWD_STATUS_PERM = "P";
	/**
	 * PWD_STATUS_LOCKED
	 */
	public static final String PWD_STATUS_LOCKED = "L";
	/**
	 * PWD_STATUS_REVOKED
	 */
	public static final String PWD_STATUS_REVOKED = "R";
	/**
	 * PWD_STATUS_TEMPORARY
	 */
	public static final String PWD_STATUS_TEMPORARY = "T";
	/**
	 * PWD_ALGO_ALGO1
	 */
	public static final String PWD_ALGO_ALGO1 = "algo1";
	/**
	 * PWD_MAX_ATTEMPTS
	 */
	public static final int PWD_MAX_ATTEMPTS = 5;
	
	/**
	 * ONLINEID_IND
	 */
	public static final String ONLINEID_IND = "OID";
	/**
	 * GUID_IND
	 */
	public static final String GUID_IND = "GUID";
	/**
	 * EMAIL_IND
	 */
	public static final String EMAIL_IND = "EMAIL";
	/**
	 * APP_SIAAM
	 */
	public static final String APP_SIAAM = "SiaamApp";
	
	/**
	 * OID_STATUS_OPEN
	 */
	public static final String OID_STATUS_OPEN = "O";
	/**
	 * OID_STATUS_CLOSED
	 */
	public static final String OID_STATUS_CLOSED = "C";
	/**
	 * OID_STATUS_PENDING
	 */
	public static final String OID_STATUS_PENDING = "P";
	
	/**
	 * SUCCESS
	 */
	public static final int SUCCESS = 1;
	/**
	 * Header name for JWE token.
	 */
	public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
}
