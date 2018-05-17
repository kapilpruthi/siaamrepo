package com.siaam.auth.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.siaam.auth.utils.SiaamWebConstants;;

/**
 * User Data Transfer Object for Pwd reset.
 * @author Kapil Pruthi
 */
public class ResetPwdDto {

	/**
	 * onlineId of the user.
	 * onlineId should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "onlineId missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_OID, message = "OnlineId format is invalid.")
	private String onlineId;

	/**
	 * new pwd user wants to set.
	 * new pwd should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "New pwd missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_PWD, message = "New Password format is invalid.")
	private String newPwd;
	
	/**
	 * confirm pwd.
	 * confirm pwd should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "confirm pwd missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_PWD, message = "Confirm Password format is invalid.")
	private String confirmNewPwd;

	/**
	 * @return the confirmNewPwd
	 */
	public String getConfirmNewPwd() {
		return confirmNewPwd;
	}

	/**
	 * @param confirmNewPwd the confirmNewPwd to set
	 */
	public void setConfirmNewPwd(String confirmNewPwd) {
		this.confirmNewPwd = confirmNewPwd;
	}

	/**
	 * @return the newPwd
	 */
	public String getNewPwd() {
		return newPwd;
	}

	/**
	 * @return the onlineId
	 */
	public String getOnlineId() {
		return onlineId;
	}

	/**
	 * @param onlineId
	 *            the onlineId to set
	 */
	public void setOnlineId(String onlineId) {
		this.onlineId = onlineId;
	}

	/**
	 * @param newPwd
	 *            the newPwd to set
	 */
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	/**
	 * @return true if password and confirm password are same
	 */
	@AssertTrue(message = "password and confirm pwd are NOT same")
	private boolean isValidInput() {
		return (newPwd.equals(confirmNewPwd) ? true : false);
	}
}