package com.siaam.auth.service;


import com.siaam.auth.exception.AbstractBusinessException;
import com.siaam.sdm.auth.User;

/**
 * Interface for User Maintenance Operations.
 * @author Kapil Pruthi
 */
public interface SiaamUserOperationsSO {
	
	/**
	 * Change onlineId for a given user.
	 * @param guid unique id of the user
	 * @param newOnlineId new online id to be set
	 * @return true if id changed successfully else false
	 * @throws AbstractBusinessException Exception
	 */
	boolean changeOnlineId(String guid, String newOnlineId) throws AbstractBusinessException;

	/**
	 * change pwd for the user in authenticated session.
	 * @param guid unique id of the user
	 * @param currPwd current pwd
	 * @param newPwd new pwd
	 * @return true if pwd changed successfully else false
	 * @throws AbstractBusinessException Exception
	 */
	boolean changePwd(String guid, String currPwd, String newPwd) throws AbstractBusinessException;

	/**
	 * reset pwd in case user forgot current pwd.
	 * @param guid unique id of the user
	 * @param newPwd new pwd
	 * @return true if pwd changed successfully else false
	 * @throws AbstractBusinessException Exception
	 */
	boolean resetPwd(String guid, String newPwd) throws AbstractBusinessException;
	
	/**
	 * Look up user based on input onlineId/email combo, 
	 * if matches create a JWT token for GUID & return
	 * @param onlineId online id of the user
	 * @param email Email supplied by user
	 * @return token String
	 * @throws AbstractBusinessException Exception
	 */
	String createOTTForResetPwd(String onlineId, String email) throws AbstractBusinessException;
	/**
	 * Parse JWT Token, Fetch GUID & look up user based on GUID.
	 * Compare onlineId supplied vs onlineId in token.
	 * If token valid & user found, return guid of user.  
	 * @param onlineId online id of the user
	 * @param token Token
	 * @return onlineId
	 * @throws AbstractBusinessException Exception
	 */
	String validateOTTForResetPwd(String onlineId, String token) throws AbstractBusinessException;
	/**
	 * API can be used to set status like- unlock, lock, revoke, expire pwd.
	 * @param guid unique id of the user
	 * @param pwdStatus new pwd status to be set
	 * @return true if pwd status changed successfully else false
	 * @throws AbstractBusinessException Exception
	 */
	boolean updatePwdStatus(String guid, String pwdStatus) throws AbstractBusinessException;
	
	/**
	 * @param guid unique id of the user
	 * @param firstName new first name to be updated
	 * @param lastName new last name to be updated
	 * @return true if legal name updated successfully else false
	 * @throws AbstractBusinessException Exception
	 */
	boolean updateLegalName(String guid, String firstName, String lastName) throws AbstractBusinessException;
	
	/**
	 * @param guid unique id of the user
	 * @param email new email to be updated
	 * @return true if email updated successfully else false
	 * @throws AbstractBusinessException Exception
	 */
	boolean updateEmail(String guid, String email) throws AbstractBusinessException;
	
	/**
	 * @param user populated User object if call is already made.
	 * @param guid unique id of the user
	 * @param pwd user supplied pwd
	 * @return true if pwd supplied is good else false
	 * @throws AbstractBusinessException Exception
	 */
	boolean validatePwd(User user, String guid, String pwd)
			throws AbstractBusinessException;

	/**
	 * Update last login ts
	 * @param guid unique id of the user
	 * @return true if last login ts updated fine. 
	 * @throws AbstractBusinessException
	 */
	boolean updateLastLoginTs(String guid);

}