package com.siaam.device.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.siaam.sdm.device.Device;
import com.siaam.sdm.device.UserDevice;


/**
 * User DAO Interface to perform CRUD operations on the UserDevice table.
 * @author Kapil Pruthi
 */
public interface UserDeviceDao {
	
	/**
	 * Create new User Device.
	 * @param userDevice UserDevice
	 * @return status code
	 * @throws DataAccessException Exception
	 */
	int createUserDevice(UserDevice userDevice) throws DataAccessException;
	
	/**
	 * Search device record based on deviceId.
	 * @param userId guid
	 * @return List of Device objs
	 * @throws DataAccessException Exception
	 */
	List<Device> findAllDevicesOfUser(String userId) throws DataAccessException;
	/** 
	 * Delete all devices for an user.
	 * @param userId guid
	 * @return status code
	 * @throws DataAccessException Exception
	 */
    int deleteAllDevicesOfUser(String userId) throws DataAccessException;
}