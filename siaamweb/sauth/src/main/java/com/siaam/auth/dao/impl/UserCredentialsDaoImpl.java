package com.siaam.auth.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.siaam.auth.dao.UserCredentialsDao;

/**
 * Dao to provide specific column update operations on User Table.
 * 
 * @author kapil pruthi
 */
@Repository
public class UserCredentialsDaoImpl extends BaseDaoImpl implements UserCredentialsDao {

	/**
	 * Sql to Update User Pwd Lock Ctr and Pwd Status.
	 */
	public static final String SQL_UPDATE_PWD_LOCK_CTR_STAT = "UPDATE SIAAMDB.USER SET PASSWORD_LOCK_CNT=?,"
			+ " PASSWORD_STATUS=? WHERE GUID=?";

	/**
	 * Sql to Update User Pwd status.
	 */
	public static final String SQL_UPDATE_PWD_STATUS = "UPDATE SIAAMDB.USER SET PASSWORD_STATUS=? WHERE GUID=?";

	/**
	 * Sql to Update User Pwd.
	 */
	public static final String SQL_UPDATE_PWD = "UPDATE SIAAMDB.USER SET PASSWORD=?, PASSWORD_LOCK_CNT=0 WHERE GUID=?";

	/**
	 * Sql to Update User Online id.
	 */
	public static final String SQL_UPDATE_ONLINE_ID = "UPDATE SIAAMDB.USER SET ONLINE_ID=? WHERE GUID=?";

	/**
	 * Sql to Update User onlineId status.
	 */
	public static final String SQL_UPDATE_ONLINE_ID_STAT = "UPDATE SIAAMDB.USER SET ONLINE_ID_STAT=? WHERE GUID=?";

	/**
	 * Sql to Update User First & Last name.
	 */
	public static final String SQL_UPDATE_NAME = "UPDATE SIAAMDB.USER SET FIRST_NAME=?, LAST_NAME=? WHERE GUID=?";

	/**
	 * Sql to Update User Email.
	 */
	public static final String SQL_UPDATE_EMAIL = "UPDATE SIAAMDB.USER SET EMAIL=? WHERE GUID=?";

	/**
	 * Sql to Update User Last login Ts.
	 */
	public static final String SQL_UPDATE_LAST_LOGIN_TS = "UPDATE SIAAMDB.USER SET LAST_LOGIN_TS=SYSDATE() WHERE GUID=?";

	/**
	 * Sql to read User Pwd.
	 */
	public static final String SQL_READ_PWD = "SELECT PASSWORD FROM SIAAMDB.USER WHERE GUID=?";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siaam.auth.dao.UserCredentialsDao#updatePwdLockCtAndStatus(java.lang.
	 * String)
	 */
	@Override
	public int updatePwdLockCtAndStatus(final String guid, final int lockCtr, final String pwdStatus)
			throws DataAccessException {
		return getJdbcTemplate().update(SQL_UPDATE_PWD_LOCK_CTR_STAT, new Object[] {lockCtr, pwdStatus, guid });
	}

	@Override
	public int updatePwdStatus(final String guid, final String pwdStatus) throws DataAccessException {
		return getJdbcTemplate().update(SQL_UPDATE_PWD_STATUS, new Object[] {pwdStatus, guid });
	}

	@Override
	public int updatePwd(final String guid, final String clearTextPwd) throws DataAccessException {
		return getJdbcTemplate().update(SQL_UPDATE_PWD, new Object[] {clearTextPwd, guid });
	}

	@Override
	public int updateOnlineId(final String guid, final String newOnlineId) throws DataAccessException {
		return getJdbcTemplate().update(SQL_UPDATE_ONLINE_ID, new Object[] {newOnlineId, guid });
	}

	@Override
	public int updateOnlineIdStatCd(final String guid, final String onlineIdStatCd) throws DataAccessException {
		return getJdbcTemplate().update(SQL_UPDATE_ONLINE_ID_STAT, new Object[] {onlineIdStatCd, guid });
	}

	@Override
	public int updateLegalName(String guid, String firstName, String lastName) throws DataAccessException {
		return getJdbcTemplate().update(SQL_UPDATE_NAME, new Object[] {firstName, lastName, guid });
	}

	@Override
	public int updateEmail(String guid, String email) throws DataAccessException {
		return getJdbcTemplate().update(SQL_UPDATE_EMAIL, new Object[] {email, guid });
	}

	@Override
	public int updateLastLoginTs(String guid) throws DataAccessException {
		return getJdbcTemplate().update(SQL_UPDATE_LAST_LOGIN_TS, new Object[] {guid});
	}
}
