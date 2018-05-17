
package com.siaam.auth.dao;

import org.springframework.dao.DataAccessException;

/**
 * Perform updates to Siaam Db w.r.t User Credentials.
 * @author kapil pruthi
 *
 */
public interface UserCredentialsDao {

	/**
	 * Update pwd lock ctr & status in Db.
	 * @param guid unique id of user
	 * @param currentPwdLockCt current Pwd LockCtr of user
	 * @param currentPwdStatus current Pwd Status of user
	 * @return status
	 * @throws DataAccessException Exception
	 */
	int updatePwdLockCtAndStatus(String guid, int currentPwdLockCt, String currentPwdStatus) throws DataAccessException;
	/**
	 * update pwd status to supplied value.
	 * @param  guid unique id of user
	 * @param pwdStatus pwd status
	 * @return status
	 * @throws DataAccessException Exception
	 */
	int updatePwdStatus(String guid, String pwdStatus) throws DataAccessException;
	/**
	 * update pwd in database for the user.
	 * @param  guid unique id of user
	 * @param clearTextPwd clear text pwd
	 * @return status
	 * @throws DataAccessException Exception
	 */
	int updatePwd(String guid, String clearTextPwd) throws DataAccessException;

	/**
	 * Change onlineId of a user.
	 * @param  guid unique id of user
	 * @param newOnlineId new Online Id of the user.
	 * @return status
	 * @throws DataAccessException Exception
	 */
	int updateOnlineId(String guid, String newOnlineId) throws DataAccessException;

	/**
	 * Update Legal name for a given onlineId.
	 * @param  guid unique id of user
	 * @param firstName first name of user
	 * @param lastName last name of user
	 * @return status
	 * @throws DataAccessException Exception
	 */
	int updateLegalName(String guid, String firstName, String lastName) throws DataAccessException;
	
	/**
	 * Update email for a given onlineId.
	 * @param  guid unique id of user
	 * @param email emailId of the user.
	 * @return status
	 * @throws DataAccessException Exception
	 */
	int updateEmail(String guid, String email) throws DataAccessException;
	
	/**
	 * Change onlineId of a user.
	 * @param  guid unique id of user
	 * @param onlineIdStatCd OID status code.
	 * @return status
	 * @throws DataAccessException Exception
	 */
	int updateOnlineIdStatCd(String guid, String onlineIdStatCd) throws DataAccessException;
	
	/**
	 * Update last login ts in user table.
	 * @param  guid unique id of user
	 * @return status
	 * @throws DataAccessException Exception
	 */
	int updateLastLoginTs(String guid) throws DataAccessException;
}
