package com.siaam.auth.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.siaam.auth.utils.SiaamWebConstants;;

/**
 * User Data Transfer Object for id change.
 * @author Kapil Pruthi
 */
public class EditIdDto {

	/**
	 * current onlineId of the user.
	 * onlineId should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Current onlineId missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_OID, message = "Current OnlineId format is invalid.")
	private String currOnlineId;

	/**
	 * new onlineId user want to set.
	 * onlineId should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "New onlineId missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_OID, message = "New onlineId format is invalid.")
	private String newOnlineId;

	/**
	 * @return true if new onlineId is different from current.
	 */
	@AssertTrue(message = "No change detected. Both current and new onlineid are same")
	private boolean isValidInput() {
		return (currOnlineId.equalsIgnoreCase(newOnlineId) ? false : true);
	}

	/**
	 * @return the currOnlineId
	 */
	public String getCurrOnlineId() {
		return currOnlineId;
	}

	/**
	 * @param currOnlineId
	 *            the currOnlineId to set
	 */
	public void setCurrOnlineId(String currOnlineId) {
		this.currOnlineId = currOnlineId;
	}

	/**
	 * @return the newOnlineId
	 */
	public String getNewOnlineId() {
		return newOnlineId;
	}

	/**
	 * @param newOnlineId
	 *            the newOnlineId to set
	 */
	public void setNewOnlineId(String newOnlineId) {
		this.newOnlineId = newOnlineId;
	}

}