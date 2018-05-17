/**
 * 
 */
package com.siaam.device.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.siaam.device.dao.DeviceDao;
import com.siaam.sdm.device.Device;

/**
 * Implementation for DeviceDao Operations.
 * 
 * @author kapil pruthi
 */
@Repository
public class DeviceDaoImpl extends BaseDaoImpl implements DeviceDao {

	/**
	 * Sql to create a device.
	 */
	public static final String SQL_CREATE_DEVICE = "INSERT INTO SIAAMDB.DEVICE "
			+ "(ID, CATEGORY, TYPENAME, FAMILY, VERSION, OS, IP, LOCATION, USERAGENT,"
			+ "CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,SYSDATE(),?,SYSDATE())";

	/**
	 * Sql to read a device.
	 */
	public static final String SQL_READ_DEVICE = "SELECT * FROM SIAAMDB.DEVICE WHERE ID=?";

	/**
	 * Sql to update a device.
	 */
	public static final String SQL_UPDATE_DEVICE = "UPDATE SIAAMDB.DEVICE SET ID=?, "
			+ "CATEGORY=?, TYPENAME=?, FAMILY=?, VERSION=?, OS=?, IP=?, LOCATION=?, "
			+ "USERAGENT=?, UPDATED_BY=?, UPDATED_DATE=SYSDATE()  WHERE ID=?";

	/**
	 * Sql to delete a device.
	 */
	public static final String SQL_DELETE_DEVICE = "DELETE FROM SIAAMDB.DEVICE WHERE ID=?";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siaam.device.dao.DeviceDao#createDevice(com.siaam.sdm.device.Device)
	 */
	@Override
	public int createDevice(Device device) throws DataAccessException {
		return getJdbcTemplate().update(SQL_CREATE_DEVICE,
				new Object[] {device.getDeviceId(), device.getCategory(), 
						device.getTypeName(), device.getFamily(), device.getVersion(), 
						device.getOs(), device.getIp(), device.getLocation(), 
						device.getUserAgent(), device.getAuditgroup().getCreatedBy(), 
						device.getAuditgroup().getUpdatedBy() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siaam.device.dao.DeviceDao#findDevice(java.lang.String)
	 */
	@Override
	public Device findDevice(String deviceId) throws DataAccessException {
		return getJdbcTemplate().queryForObject(SQL_READ_DEVICE, new String[] {deviceId }, new DeviceMapper());
	}

	/*
	 * (non-Javadoc)
	 * @see com.siaam.device.dao.DeviceDao#updateDevice(java.lang.String,
	 * com.siaam.sdm.device.Device)
	 */
	@Override
	public int updateDevice(String deviceId, Device device) throws DataAccessException {
		return getJdbcTemplate().update(SQL_UPDATE_DEVICE,
				new Object[] {device.getDeviceId(), device.getCategory(), device.getTypeName(), device.getFamily(),
						device.getVersion(), device.getOs(), device.getIp(), device.getLocation(),
						device.getUserAgent(), device.getAuditgroup().getUpdatedBy(), deviceId });
	}

	/*
	 * (non-Javadoc)
	 * @see com.siaam.device.dao.DeviceDao#deleteDevice(java.lang.String)
	 */
	@Override
	public int deleteDevice(String deviceId) throws DataAccessException {
		return getJdbcTemplate().update(SQL_DELETE_DEVICE, new Object[] {deviceId });
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
