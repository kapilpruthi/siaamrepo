package com.siaam.auth.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.siaam.auth.utils.SiaamWebConstants;;

/**
 * User Data Transfer Object for Email change.
 * @author Kapil Pruthi
 */
public class EditEmailDto {

	/**
	 * current pwd of the user.
	 * current pwd should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Current pwd missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_PWD, message = "Current Password format is invalid.")
	private String currPwd;

	/**
	 * Email Id of the user.
	 * Email should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Please enter your Email.")
	@Email(message = "Email format is invalid.")
	private String email;
	
	/**
	 * Confirm Email Id of the user.
	 * Confirm Email should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Please confirm the Email.")
	@Email(message = "Email format is invalid.")
	private String confirmEmail;

	/**
	 * @return true if new onlineId is different from current.
	 */
	@AssertTrue(message = "Please make sure email and confirmation are same")
	private boolean isValidInput() {
		return (email.equalsIgnoreCase(confirmEmail) ? true : false);
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the currPwd
	 */
	public String getCurrPwd() {
		return currPwd;
	}

	/**
	 * @param currPwd the currPwd to set
	 */
	public void setCurrPwd(String currPwd) {
		this.currPwd = currPwd;
	}

	/**
	 * @return the confirmEmail
	 */
	public String getConfirmEmail() {
		return confirmEmail;
	}

	/**
	 * @param confirmEmail the confirmEmail to set
	 */
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

}