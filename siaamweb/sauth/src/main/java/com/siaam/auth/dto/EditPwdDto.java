package com.siaam.auth.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.siaam.auth.utils.SiaamWebConstants;;

/**
 * User Data Transfer Object for Pwd change. 
 * @author Kapil Pruthi
 */
public class EditPwdDto {

	/**
	 * current pwd of the user.
	 * current pwd should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Current pwd missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_PWD, message = "Current Password format is invalid.")
	private String currPwd;

	/**
	 * new pwd user want to create.
	 * new pwd should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "New pwd missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_PWD, message = "New Password format is invalid.")
	private String newPwd;

	/**
	 * confirmNewPwd user want to create.
	 * confirmNewPwd should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Confirm New Pwd pwd missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_PWD, message = "New Password format is invalid.")
	private String confirmNewPwd;
	
	/**
	 * @return true if confirm new pwd and new pwd are same
	 */
	@AssertTrue(message = "New pwd and confirm new pwd must be same.")
	private boolean isValidInput() {
		return (confirmNewPwd.equals(newPwd) ? true : false);
	}
	
	/**
	 * @return true if current pwd and new pwd are different
	 */
	@AssertTrue(message = "No change detected. Both current and new pwd are same")
	private boolean isNewPwdUnique() {
		return (currPwd.equals(newPwd) ? false : true);
	}

	/**
	 * @return the currPwd
	 */
	public String getCurrPwd() {
		return currPwd;
	}

	/**
	 * @param currPwd
	 *            the currPwd to set
	 */
	public void setCurrPwd(String currPwd) {
		this.currPwd = currPwd;
	}

	/**
	 * @return the newPwd
	 */
	public String getNewPwd() {
		return newPwd;
	}

	/**
	 * @param newPwd
	 *            the newPwd to set
	 */
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

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

}