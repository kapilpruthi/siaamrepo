package com.siaam.auth.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Common Validation Utils placed here.
 * @author Kapil Pruthi
 *
 */
public final class CommonValidationUtils {

	/**
	 * private constructor.
	 */
	private CommonValidationUtils() {
	}

	/**
	 * Pattern for UserName Regex.
	 */
	private static Pattern uNamePattern = Pattern.compile(SiaamWebConstants.REGEX_OID);
	/**
	 * Pattern for Password Regex.
	 */
	private static Pattern passwordPattern = Pattern.compile(SiaamWebConstants.REGEX_PWD);
	
	/**
	 * Pattern for GUID Regex.
	 */
	private static Pattern guidPattern = Pattern.compile(SiaamWebConstants.REGEX_GUID);

	/**
	 * Validate user name format.
	 * @param userName input uName string
	 * @return true if valid else false
	 */
	public static boolean isValidUserName(final String userName) {
		return isValid(userName, uNamePattern);
	}
	
	/**
	 * Validate user guid format.
	 * @param guid input guid string
	 * @return true if valid else false
	 */
	public static boolean isValidGuid(final String guid) {
		return isValid(guid, guidPattern);
	}

	/**
	 * Validate password format.
	 * @param password input pwd string
	 * @return true if valid else false
	 */
	public static boolean isValidPassword(final String password) {
		return isValid(password, passwordPattern);
	}

	/**
	 * RegEx validation on a string with supplied Pattern.
	 * @param inStr Input String
	 * @param inPattern PreCompiled Pattern for a given RegEx
	 * @return true or false
	 */
	private static boolean isValid(final String inStr, final Pattern inPattern) {
		Matcher matcher = inPattern.matcher(inStr);
		return matcher.matches();
	}
}
