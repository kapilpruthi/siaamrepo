package com.siaam.auth.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;;

/**
 * User Data Transfer Object for Id lookup.
 * @author Kapil Pruthi
 */
public class ForgotIdDto {

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

}