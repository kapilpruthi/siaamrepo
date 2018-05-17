package com.siaam.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.siaam.auth.dao.UserCredentialsDao;
import com.siaam.auth.exception.AbstractBusinessException;
import com.siaam.auth.exception.DBAccessException;
import com.siaam.auth.exception.ValidationException;
import com.siaam.auth.service.SiaamUserOperationsSO;
import com.siaam.auth.service.SiaamUserSO;
import com.siaam.auth.token.TokenService;
import com.siaam.auth.utils.CommonUtils;
import com.siaam.auth.utils.SiaamWebConstants;
import com.siaam.sdm.auth.User;

/**
 * Service implementation for User Maintenance Operations.
 * Assumption is all API inputs are pre-validated in Controllers/Filters.
 * @author Kapil Pruthi
 */
public class SiaamUserOperationsSOImpl implements SiaamUserOperationsSO {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SiaamUserOperationsSOImpl.class);
	
	/**
	 * siaamUserSO for user CRUD.
	 */
	@Autowired
	private SiaamUserSO siaamUserSO;
	
	/**
	 * tokenService for creating short lived tokens for reset operations.
	 */
	@Autowired
	private TokenService tokenService;
	
	/**
	 * User Credentials Dao.
	 */
	@Autowired
	private UserCredentialsDao userCredentialsDao;
	
	/**
	 * We need an BCrypt encoder since our passwords in the database are BCrypt
	 * encoded.
	 */
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	/* (non-Javadoc)
	 * @see com.siaam.auth.service.SiaamUserOperationsSO#changeOnlineId(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean changeOnlineId(String guid, String newOnlineId) throws AbstractBusinessException {
		int result = 0;
		try {
			result = userCredentialsDao.updateOnlineId(guid, newOnlineId);
		} catch (DataAccessException dEx) {
			LOGGER.error(" Exception in changeOnlineId " + dEx.getMessage());
			throw new DBAccessException(dEx.getCause());
		}
		return returnResult(result, "Id not changed");
	}

	/* (non-Javadoc)
	 * @see com.siaam.auth.service.SiaamUserOperationsSO#changePwd(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean changePwd(String guid, String currPwd, String newPwd) throws AbstractBusinessException {
		
		// validate current pwd in Db with supplied pwd
		if (!validatePwd(null, guid, currPwd)) {
			throw new ValidationException("Current pwd suppiled doesnt match with current pwd in Db");
		}
		//if matches, bcrypt new pwd & persist in db
		int result = 0;
		try {
			result = userCredentialsDao.updatePwd(guid, CommonUtils.generateHash(newPwd));
		} catch (DataAccessException dEx) {
			LOGGER.error(" Exception in updatePwd " + dEx.getMessage());
			throw new DBAccessException(dEx.getCause());
		}
		return returnResult(result, "Pwd not changed");
	}

	/* (non-Javadoc)
	 * @see com.siaam.auth.service.SiaamUserOperationsSO#resetPwd(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean resetPwd(String guid, String newPwd) throws AbstractBusinessException {
		int result = 0;
		try {
			result = userCredentialsDao.updatePwd(guid, CommonUtils.generateHash(newPwd));
		} catch (DataAccessException dEx) {
			LOGGER.error(" Exception in resetPwd " + dEx.getMessage());
			throw new DBAccessException(dEx.getCause());
		}
		return returnResult(result, "Pwd reset not successful.");
	}
	@Override
	public String createOTTForResetPwd(String onlineId, String email) throws AbstractBusinessException {
		User user =  null;
		try {
			user = siaamUserSO.readUser(onlineId, SiaamWebConstants.ONLINEID_IND);
		} catch (AbstractBusinessException ex) {
			LOGGER.error(ex.getMessage());
			throw new ValidationException("User not found - Please verify if onlineid & email combo is correct");
		}
		LOGGER.info("Supplied email " + email + " Email on file " + user.getEmail());
		// compare current email with supplied email
		if (!email.equalsIgnoreCase(user.getEmail())) {
			LOGGER.error("Email suppiled doesnt match with current email in Db");
			throw new ValidationException("User not found - Please verify if onlineid & email combo is correct");
		}
		// create JWT token with GUID in it & return
		return tokenService.createToken(user.getGuid(), "resetPwd", null);
	}
	/* (non-Javadoc)
	 * @see com.siaam.auth.service.SiaamUserOperationsSO#updatePwdStatus(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updatePwdStatus(String guid, String pwdStatus) throws AbstractBusinessException {
		int result = 0;
		try {
			result = userCredentialsDao.updatePwdStatus(guid, pwdStatus);
		} catch (DataAccessException dEx) {
			LOGGER.error(" Exception in updatePwdStatus " + dEx.getMessage());
			throw new DBAccessException(dEx.getCause());
		}
		return returnResult(result, "Pwd status update not successful.");
	}

	
	/**
	 * Increment pwd lock count & update pwd status.
	 * @param guid guid of user
	 * @param currentPwdStatus current pwd status
	 * @param currentLockCt current lock ctr
	 * @return true if lock/status updated successfully.
	 * @throws AbstractBusinessException Exception
	 */
	private boolean incrementPwdLockCt(String guid, String currentPwdStatus, int currentLockCt)
			throws AbstractBusinessException {
		int result = 0;
		if (currentLockCt < SiaamWebConstants.PWD_MAX_ATTEMPTS) {
			int lockCtr = currentLockCt + 1;
			String pwdStatus = currentPwdStatus;
			if (lockCtr == SiaamWebConstants.PWD_MAX_ATTEMPTS) {
				pwdStatus = "L"; // Locked
			}
			try {
				result = userCredentialsDao.updatePwdLockCtAndStatus(guid, currentLockCt, pwdStatus);
			} catch (DataAccessException dEx) {
				LOGGER.error(" Exception in incrementPwdLockCt " + dEx.getMessage());
				throw new DBAccessException(dEx.getCause());
			}
		}
		return returnResult(result, "pwd lock counter not incremented");
	}

	/* (non-Javadoc)
	 * @see com.siaam.auth.service.SiaamUserOperationsSO#updateLegalName(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateLegalName(String guid, String firstName, String lastName)
			throws AbstractBusinessException {
		int result = 0;
		try {
			result = userCredentialsDao.updateLegalName(guid, firstName, lastName);
		} catch (DataAccessException dEx) {
			LOGGER.error(" Exception in updateLegalName " + dEx.getMessage());
			throw new DBAccessException(dEx.getCause());
		}
		return returnResult(result, "legal name not updated");
	}

	/* (non-Javadoc)
	 * @see com.siaam.auth.service.SiaamUserOperationsSO#updateEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateEmail(String guid, String email) throws AbstractBusinessException {
		int result = 0;
		try {
			result = userCredentialsDao.updateEmail(guid, email);
		} catch (DataAccessException dEx) {
			LOGGER.error(" Exception in updateEmail " + dEx.getMessage());
			throw new DBAccessException(dEx.getCause());
		}
		return returnResult(result, "Email not updated");
	}

	/**
	 * If result is 1, return true else throw exception
	 * @param result integer
	 * @param message String
	 * @return true or false
	 * @throws DBAccessException Exception
	 */
	private boolean returnResult(int result, String message) throws DBAccessException {
		if (result == 1) {
			return true;
		} else {
			throw new DBAccessException(message);
		}
	}

	/* (non-Javadoc)
	 * @see com.siaam.auth.service.SiaamUserOperationsSO#validateOTTForResetPwd(java.lang.String)
	 */
	@Override
	public String validateOTTForResetPwd(String onlineId, String token) throws AbstractBusinessException {
		String guid = (String) (tokenService.readToken(token, "resetPwd")).getSubject();
		User user = null;
		try {
			user = siaamUserSO.readUser(guid, SiaamWebConstants.GUID_IND);
		} catch (AbstractBusinessException ex) {
			LOGGER.error(ex.getMessage());
			throw new ValidationException("User not found - Please request new token");
		}
		// TODO In future only allow 1 Reset Pwd for a time interval
		// so same token cannot be exploited multiple times.
		if (!user.getOnlineId().equalsIgnoreCase(onlineId)) {
			throw new ValidationException("Invalid onlineId supplied");
		}
		// return onlineId associated with token
		return user.getGuid();
	}

	@Override
	public boolean validatePwd(User user, String guid, String pwd) throws AbstractBusinessException {
		if (user == null) {
			user = siaamUserSO.readUser(guid, SiaamWebConstants.GUID_IND);
		}
		// Compare password locally
		// Make sure to encode the password first before comparing
		if ((passwordEncoder.matches(pwd, user.getPassword()))) {
			LOGGER.info("password validated successfully!");
			// Reset counter back to 0 and status as PERM- assuming we dont support Temp pwd in system yet
			userCredentialsDao.updatePwdLockCtAndStatus(user.getGuid(), 0, SiaamWebConstants.PWD_STATUS_PERM);
			return true;
		} else {
			LOGGER.error("Wrong pwd!");
			// increment lock counter & update pwd status
			incrementPwdLockCt(user.getGuid(), user.getPasswordStatus(), user.getPasswordLockoutCnt());
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.siaam.auth.service.SiaamUserOperationsSO#updateEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateLastLoginTs(String guid) {
		int result = 0;
		try {
			result = userCredentialsDao.updateLastLoginTs(guid);
		} catch (DataAccessException dEx) {
			LOGGER.error(" Exception in updateLastLoginTs " + dEx.getMessage());
		}
		return (result == 1 ? true : false);
	}
}
