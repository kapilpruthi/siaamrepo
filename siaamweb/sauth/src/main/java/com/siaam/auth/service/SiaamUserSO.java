package com.siaam.auth.service;


import com.siaam.auth.dto.RegisterUserDto;
import com.siaam.auth.exception.AbstractBusinessException;
import com.siaam.sdm.auth.User;

/**
 * Interface for User Master Maintenance-CRUD.
 * @author Kapil Pruthi
 */
public interface SiaamUserSO {

	/**
	 * Create a new user.
	 * @param registerDto User DTO
	 * @param client createdBy
	 * @param role String
	 * @return true or false based on user created or not
	 * @throws AbstractBusinessException Exception
	 */
	boolean createUser(RegisterUserDto registerDto, String client, String role) throws AbstractBusinessException;
	
	/**
	 * Read the user from datasource.
	 * @param uniqueId unique Id
	 * @param idType could be onlineid or guid.
	 * @return User Object.
	 * @throws AbstractBusinessException Exception
	 */
	User readUser(String uniqueId, String idType) throws AbstractBusinessException;
	
	/**
	 * Delete the user from datasource.
	 * @param uniqueId unique Id
	 * @param idType could be onlineid or guid.
	 * @return boolean true or false
	 * @throws AbstractBusinessException Exception
	 */
	boolean deleteUser(String uniqueId, String idType) throws AbstractBusinessException;

}