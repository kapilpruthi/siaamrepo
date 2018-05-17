package com.siaam.auth.dao;

import org.springframework.dao.DataAccessException;

import com.siaam.sdm.auth.User;

/**
 * User DAO Interface to perform CRUD operations on User table.
 * @author Kapil Pruthi
 */
public interface UserDao {

	/**
	 * Create new user record.
	 * @param user UserObject
	 * @return status code
	 * @throws DataAccessException Exception
	 */
	int createUser(User user) throws DataAccessException;
	/**
	 * Search user record based on onlineId.
	 * @param onlineId online_id
	 * @return User Obj
	 * @throws DataAccessException Exception
	 */
	User findUserByOnlineId(String onlineId) throws DataAccessException;
	/**
	 * Search user record based on guid.
	 * @param guid guid
	 * @return User Obj
	 * @throws DataAccessException Exception
	 */
	User findUserByGuid(String guid) throws DataAccessException;
	/**
	 * Search user record based on email. Used in reset flows.
	 * @param email email
	 * @return User Obj
	 * @throws DataAccessException Exception
	 */
	User findUserByEmail(String email) throws DataAccessException;
	/**
	 * Update existing User record.
	 * @param onlineId String
	 * @param user UserObject
	 * @return status code
	 * @throws DataAccessException Exception
	 */
	int updateUserByOnlineId(User user, String onlineId) throws DataAccessException;
	/**
	 * Update existing User record.
	 * @param guid String
	 * @param user UserObject
	 * @return status code
	 * @throws DataAccessException Exception
	 */
	int updateUserByGuid(User user, String guid) throws DataAccessException;
	/**
	 * Delete user based on onlineId.
	 * @param onlineId online_id
	 * @return status code
	 * @throws DataAccessException Exception
	 */
    int deleteUserByOnlineId(String onlineId) throws DataAccessException;
    /**
	 * Delete user based on guid.
	 * @param guid unique id for the user
	 * @return status code
	 * @throws DataAccessException Exception
	 */
    int deleteUserByGuid(String guid) throws DataAccessException;
}
