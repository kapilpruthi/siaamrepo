package com.siaam.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.siaam.auth.dao.UserDao;
import com.siaam.auth.dto.RegisterUserDto;
import com.siaam.auth.exception.AbstractBusinessException;
import com.siaam.auth.exception.DBAccessException;
import com.siaam.auth.exception.ValidationException;
import com.siaam.auth.service.SiaamUserSO;
import com.siaam.auth.utils.CommonUtils;
import com.siaam.auth.utils.SiaamWebConstants;
import com.siaam.sdm.auth.User;
import com.siaam.sdm.common.AuditSet;

/**
 * Service class for user maintenance.
 * @author Kapil Pruthi
 *
 */
public class SiaamUserSOImpl implements SiaamUserSO {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SiaamUserSOImpl.class);

	/**
	 * UserDao for Db operations in User Table.
	 */
	@Autowired
	private UserDao userDao;


	/* (non-Javadoc)
	 * @see com.siaam.auth.service.SiaamUserSO#createUser(com.siaam.auth.dto.UserDto, java.lang.String)
	 */
	@Override
	public boolean createUser(RegisterUserDto registerDto, String client, String role) throws AbstractBusinessException {
		User user = new User();
		user.setGuid(CommonUtils.generateGUID());
		user.setOnlineId(registerDto.getUsername());
		user.setRole(role);
		user.setOnlineIdStatCd(SiaamWebConstants.OID_STATUS_OPEN);
		user.setPasswordStatus(SiaamWebConstants.PWD_STATUS_PERM);
		user.setPassword(CommonUtils.generateHash(registerDto.getPassword()));
		user.setPasswordAlgo(SiaamWebConstants.PWD_ALGO_ALGO1);
		user.setPasswordLockoutCnt(0);
		user.setEnabled(true);
		user.setHowAdded(client);
		user.setFirstName(registerDto.getFirstname());
		user.setLastName(registerDto.getLastname());
		user.setEmail(registerDto.getEmail());
		AuditSet audit = new AuditSet();
		audit.setCreatedBy(client);
		audit.setUpdatedBy(client);
		audit.setSiteId("TX");
		user.setAuditgroup(audit);
		int result = 0;
		try {
			result = userDao.createUser(user);
		} catch (DataAccessException dEx) {
			LOGGER.error(" Exception in creating user " + dEx.getMessage());
			throw new DBAccessException(dEx.getCause());
		}
		return returnResult(result, "Exception in creating user");
	}

	@Override
	public User readUser(String uniqueId, String idType) throws AbstractBusinessException {
		User user = null;
		switch (idType) {
		case SiaamWebConstants.ONLINEID_IND:
			try {
				user = userDao.findUserByOnlineId(uniqueId);
			} catch (DataAccessException dAccEx) {
				throw new DBAccessException(
						"Exception in retrieving user based on onlineId " + uniqueId + " " + dAccEx.getMessage());
			}
			break;
		case SiaamWebConstants.GUID_IND:
			try {
				user = userDao.findUserByGuid(uniqueId);
			} catch (DataAccessException dAccEx) {
				throw new DBAccessException(
						"Exception in retrieving user based on guid " + uniqueId + " " + dAccEx.getMessage());
			}
			break;
		case SiaamWebConstants.EMAIL_IND:
			try {
				user = userDao.findUserByEmail(uniqueId);
			} catch (DataAccessException dAccEx) {
				throw new DBAccessException(
						"Exception in retrieving user based on email " + uniqueId + " " + dAccEx.getMessage());
			}
			break;
		default:
			throw new ValidationException("invalid id type " + idType);
		}
		return user;
	}

	@Override
	public boolean deleteUser(String uniqueId, String idType) throws AbstractBusinessException {
		int status = 0;
		switch (idType) {
		case SiaamWebConstants.ONLINEID_IND:
			try {
				status = userDao.deleteUserByOnlineId(uniqueId);
			} catch (DataAccessException dAccEx) {
				throw new DBAccessException("Exception in retrieving user based on onlineId " + dAccEx.getMessage());
			}
			break;
		case SiaamWebConstants.GUID_IND:
			try {
				status = userDao.deleteUserByGuid(uniqueId);
			} catch (DataAccessException dAccEx) {
				throw new DBAccessException("Exception in retrieving user based on guid " + dAccEx.getMessage());
			}
			break;
		default:
			throw new ValidationException("invalid id type " + idType);
		}
		return returnResult(status, "Exception in retrieving user");
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
}
