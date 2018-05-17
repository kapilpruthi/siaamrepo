package com.siaam.auth.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.siaam.auth.dao.UserDao;
import com.siaam.auth.utils.DateUtil;
import com.siaam.sdm.auth.User;

/**
 * DAO to perform CRUD operations in User table.
 * @author kapil pruthi
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	/**
	 * Sql to register an user in USER table.
	 */
	public static final String SQL_ADD_USER = "INSERT INTO SIAAMDB.USER (GUID, ONLINE_ID, ONLINE_ID_STAT,"
					+ "ROLE, PASSWORD_ALGO, PASSWORD, PASSWORD_STATUS, PASSWORD_EXP_DATE, ENABLED, PASSWORD_LOCK_CNT,"
					+ "HOW_ADDED, OPEN_DATE, LAST_LOGIN_TS, FIRST_NAME, LAST_NAME, EMAIL,"
					+ "CREATED_BY, CREATED_TS, UPDATED_BY, UPDATED_TS, SITE_ID)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ADDDATE(SYSDATE(), 90), ?, ?, ?, SYSDATE(), SYSDATE(), ?, ?, ?, ?,"
					+ " SYSDATE(), ?, SYSDATE(), ?)";

	/**
	 * Sql to find the user for a given onlineId.
	 */
	public static final String SQL_READ_USER_BY_OID = "SELECT * FROM SIAAMDB.USER WHERE ONLINE_ID=?";

	/**
	 * Sql to find the user for a given guid.
	 */
	public static final String SQL_READ_USER_BY_GUID = "SELECT * FROM SIAAMDB.USER WHERE GUID=?";
	
	/**
	 * Sql to find the user for a given email.
	 */
	public static final String SQL_READ_USER_BY_EMAIL = "SELECT * FROM SIAAMDB.USER WHERE EMAIL=?";
	
	/**
	 * Sql to Update User record.
	 */
	public static final String SQL_UPDATE_USER = "UPDATE SIAAMDB.USER SET ONLINE_ID_STAT=?, "
					+ "ROLE=?, PASSWORD_ALGO=?, PASSWORD=?, PASSWORD_STATUS=?, ENABLED=?, PASSWORD_LOCK_CNT=?, "
					+ "UPDATED_BY=?, UPDATED_TS=SYSDATE(), SITE_ID=? WHERE ONLINE_ID=?";

	/**
	 * Sql to Delete User record.
	 */
	public static final String SQL_DELETE_USER_BY_OID = "DELETE FROM SIAAMDB.USER WHERE ONLINE_ID=?";

	/**
	 * Sql to Delete User record.
	 */
	public static final String SQL_DELETE_USER_BY_GUID = "DELETE FROM SIAAMDB.USER WHERE GUID=?";

	/* (non-Javadoc)
	 * @see com.siaam.auth.dao.UserDao#createUser(com.siaam.sdm.auth.User)
	 */
	@Override
	public int createUser(final User user) throws DataAccessException {
		return getJdbcTemplate().update(SQL_ADD_USER,
				new Object[] {user.getGuid(), user.getOnlineId(), user.getOnlineIdStatCd(), user.getRole(),
						user.getPasswordAlgo(), user.getPassword(), user.getPasswordStatus(), user.isEnabled(),
						user.getPasswordLockoutCnt(), user.getHowAdded(), user.getFirstName(),
						user.getLastName(), user.getEmail(), user.getAuditgroup().getCreatedBy(),
						user.getAuditgroup().getUpdatedBy(), user.getAuditgroup().getSiteId()});
	}

	/* (non-Javadoc)
	 * @see com.siaam.auth.dao.UserDao#findUserByOnlineId(java.lang.String)
	 */
	@Override
	public User findUserByOnlineId(String onlineId) throws DataAccessException {
		return getJdbcTemplate().queryForObject(SQL_READ_USER_BY_OID, new String[] {onlineId}, new UserMapper());
	}

	/* (non-Javadoc)
	 * @see com.siaam.auth.dao.UserDao#findUserByGuid(java.lang.String)
	 */
	@Override
	public User findUserByGuid(String guid) throws DataAccessException {
		return getJdbcTemplate().queryForObject(SQL_READ_USER_BY_GUID, new String[] {guid}, new UserMapper());
	}
	
	/* (non-Javadoc)
	 * @see com.siaam.auth.dao.UserDao#findUserByEmail(java.lang.String)
	 */
	@Override
	public User findUserByEmail(String email) throws DataAccessException {
		return getJdbcTemplate().queryForObject(SQL_READ_USER_BY_EMAIL, new String[] {email}, new UserMapper());
	}
	
	/* (non-Javadoc)
	 * @see com.siaam.auth.dao.UserDao#updateUserByOnlineId(com.siaam.sdm.auth.User, java.lang.String)
	 */
	@Override
	public int updateUserByOnlineId(User user, String onlineId) throws DataAccessException {
		return getJdbcTemplate().update(SQL_UPDATE_USER,
				new Object[] {user.getOnlineIdStatCd(), user.getRole(), user.getPasswordAlgo(), user.getPassword(),
						user.getPasswordStatus(), user.isEnabled(), user.getPasswordLockoutCnt(),
						user.getAuditgroup().getUpdatedBy(), user.getAuditgroup().getSiteId(), user.getOnlineId()});
	}
	
	/* (non-Javadoc)
	 * @see com.siaam.auth.dao.UserDao#updateUserByGuid(com.siaam.sdm.auth.User, java.lang.String)
	 */
	@Override
	public int updateUserByGuid(User user, String guid) throws DataAccessException {
		return getJdbcTemplate().update(SQL_UPDATE_USER,
				new Object[] {user.getOnlineIdStatCd(), user.getRole(), user.getPasswordAlgo(), user.getPassword(),
						user.getPasswordStatus(), user.isEnabled(), user.getPasswordLockoutCnt(),
						user.getAuditgroup().getUpdatedBy(), user.getAuditgroup().getSiteId(), user.getOnlineId()});
	}

	/* (non-Javadoc)
	 * @see com.siaam.auth.dao.UserDao#deleteUserByOnlineId(java.lang.String)
	 */
	@Override
	public int deleteUserByOnlineId(String onlineId) throws DataAccessException {
		getJdbcTemplate().update(SQL_DELETE_USER_BY_OID, new Object[] {onlineId});
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.siaam.auth.dao.UserDao#deleteUserByGuid(java.lang.String)
	 */
	@Override
	public int deleteUserByGuid(String guid) throws DataAccessException {
		getJdbcTemplate().update(SQL_DELETE_USER_BY_GUID, new Object[] {guid});
		return 1;
	}
	
	/**
	 * RowMapper class for User object.
	 *
	 * @author Kapil
	 *
	 */
	final class UserMapper implements RowMapper<User> {

		/**
		 * Map row of resultSet to User object.
		 * @param rs - resultSet
		 * @param rowNum rowNumber
		 * @return User
		 */
		@Override
		public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			User user = new User();
			user.setOnlineId(rs.getString("ONLINE_ID"));
			user.setGuid(rs.getString("GUID"));
			user.setRole(rs.getString("ROLE"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setPasswordAlgo(rs.getString("PASSWORD_ALGO"));
			user.setPasswordStatus(rs.getString("PASSWORD_STATUS"));
			user.setEmail(rs.getString("EMAIL"));
			user.setFirstName(rs.getString("FIRST_NAME"));
			user.setLastName(rs.getString("LAST_NAME"));
			
			if (rs.getTimestamp("LAST_LOGIN_TS") != null) {
				user.setLastLoginTs(DateUtil.convertDateToXMLGC(rs.getTimestamp("LAST_LOGIN_TS")));
			}
			if (rs.getTimestamp("PASSWORD_EXP_DATE") != null) {
				user.setPasswordExpDate(DateUtil.convertDateToXMLGC(rs.getTimestamp("PASSWORD_EXP_DATE")));
			}
			user.setPasswordLockoutCnt(rs.getInt("PASSWORD_LOCK_CNT"));
		return user;
		}

	}
}

