package com.siaam.auth.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.siaam.auth.utils.SiaamWebConstants;;

/**
 * User Data Transfer Object for Email change.
 * @author Kapil Pruthi
 */
public class ResetPwdRequestDto {

	/**
	 * onlineId of the user.
	 * onlineId should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "onlineId missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_OID, message = "OnlineId format is invalid.")
	private String onlineId;

	/**
	 * Email Id of the user.
	 * Email should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Please enter your Email.")
	@Email(message = "Email format is invalid.")
	private String email;

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
	 * @return the onlineId
	 */
	public String getOnlineId() {
		return onlineId;
	}

	/**
	 * @param onlineId the onlineId to set
	 */
	public void setOnlineId(String onlineId) {
		this.onlineId = onlineId;
	}
}