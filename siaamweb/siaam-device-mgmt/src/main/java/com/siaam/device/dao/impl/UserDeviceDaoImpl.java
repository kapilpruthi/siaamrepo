/**
 * 
 */
package com.siaam.device.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.siaam.device.dao.UserDeviceDao;
import com.siaam.sdm.device.Device;
import com.siaam.sdm.device.UserDevice;

/**
 * Implementation of UserDeviceDao.
 * This class provide operations to create/read/delete 
 * user and device mappings. 
 * @author kapil pruthi
 */
@Repository
public class UserDeviceDaoImpl extends BaseDaoImpl implements UserDeviceDao {

	/**
	 * Sql to create a user and device mapping
	 */
	public static final String SQL_CREATE_USER_DEVICE = "INSERT INTO SIAAMDB.USER_DEVICE "
			+ "(GUID, DEVICE_ID,CREATED_BY,CREATED_DATE,UPDATED_BY,"
			+ "UPDATED_DATE) VALUES (?,?,?,SYSDATE(),?,SYSDATE())";

	/**
	 * Sql to read a device based on device id.
	 */
	public static final String SQL_READ_USER_DEVICES = "SELECT * FROM SIAAMDB.DEVICE WHERE ID IN "
			+ "(SELECT DEVICE_ID FROM SIAAMDB.USER_DEVICE WHERE GUID=?)";

	/**
	 * Sql to delete all devices of an user.
	 * User/Device mapping is auto deleted as a cascade effect of FK relationship in DB.
	 */
	public static final String SQL_DELETE_DEVICES = "DELETE FROM SIAAMDB.DEVICE WHERE ID IN "
			+ "(SELECT DEVICE_ID FROM SIAAMDB.USER_DEVICE WHERE GUID=?)";
	
	
	/* (non-Javadoc)
	 * @see com.siaam.device.dao.UserDeviceDao#createUserDevice(com.siaam.sdm.device.UserDevice)
	 */
	@Override
	public int createUserDevice(UserDevice userDevice) throws DataAccessException {
		return getJdbcTemplate().update(SQL_CREATE_USER_DEVICE,
				new Object[] {userDevice.getGuid(), userDevice.getDeviceId(),
						userDevice.getAuditgroup().getCreatedBy(), userDevice.getAuditgroup().getUpdatedBy() });
	}

	/* (non-Javadoc)
	 * @see com.siaam.device.dao.UserDeviceDao#findAllDevicesOfUser(java.lang.String)
	 */
	@Override
	public List<Device> findAllDevicesOfUser(String userId) throws DataAccessException {
		List<Device> devices = getJdbcTemplate().query(SQL_READ_USER_DEVICES, new String[] {userId }, new DeviceMapper());
		return devices;
	}

	/* (non-Javadoc)
	 * @see com.siaam.device.dao.UserDeviceDao#deleteAllDevicesOfUser(java.lang.String)
	 */
	@Override
	public int deleteAllDevicesOfUser(String userId) throws DataAccessException {
		return getJdbcTemplate().update(SQL_DELETE_DEVICES, new Object[] {userId });
	}

	/**
	 * RowMapper class for Device object.
	 *
	 * @author Kapil
	 *
	 */
	final class DeviceMapper implements RowMapper<Device> {

		/**
		 * Map row of resultSet to Device object.
		 * 
		 * @param rs
		 *            - resultSet
		 * @param rowNum
		 *            rowNumber
		 * @return User
		 */
		@Override
		public Device mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			Device device = new Device();
			device.setDeviceId(rs.getString("ID"));
			device.setIp(rs.getString("IP"));
			device.setLocation(rs.getString("LOCATION"));
			device.setTypeName(rs.getString("TYPENAME"));
			device.setCategory(rs.getString("CATEGORY"));
			device.setOs(rs.getString("OS"));
			device.setFamily(rs.getString("FAMILY"));
			device.setVersion(rs.getString("VERSION"));
			device.setUserAgent(rs.getString("USERAGENT"));

			return device;
		}

	}

}
