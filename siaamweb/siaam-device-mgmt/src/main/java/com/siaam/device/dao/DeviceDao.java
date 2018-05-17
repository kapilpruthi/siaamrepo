package com.siaam.device.dao;

import org.springframework.dao.DataAccessException;

import com.siaam.sdm.device.Device;


/**
 * User DAO Interface to perform CRUD operations on Devices table.
 * @author Kapil Pruthi
 */
public interface DeviceDao {
	
	/**
	 * Create new device record.
	 * @param device Device
	 * @return status code
	 * @throws DataAccessException Exception
	 */
	int createDevice(Device device) throws DataAccessException;
	
	/**
	 * Search device record based on deviceId.
	 * @param deviceId Device Id
	 * @return Device Object
	 * @throws DataAccessException Exception
	 */
	Device findDevice(String deviceId) throws DataAccessException;
	/**
	 * Update existing User record.
	 * @param deviceId current deviceId
	 * @param device DeviceObject
	 * @return status code
	 * @throws DataAccessException Exception
	 */
	int updateDevice(String deviceId, Device device) throws DataAccessException;
	/** 
	 * Delete user based on onlineId.
	 * @param deviceId Device Id
	 * @return status code
	 * @throws DataAccessException Exception
	 */
    int deleteDevice(String deviceId) throws DataAccessException;
}