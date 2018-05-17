package com.siaam.auth.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.siaam.auth.utils.SiaamWebConstants;;

/**
 * User Data Transfer Object for Legal Name change. 
 * @author Kapil Pruthi
 */
public class EditLegalNameDto {

	/**
	 * onlineId of the user.
	 * onlineId should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "onlineId missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_OID, message = "OnlineId format is invalid.")
	private String onlineId;

	/**
	 * First Name, the user want to set.
	 * First Name should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "First name is missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_FIRST_NAME, message = "First Name format is invalid.")
	private String firstName;

	/**
	 * Last Name, the user want to set.
	 * Last Name should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Last Name is missing.")
	@Pattern(regexp = SiaamWebConstants.REGEX_LAST_NAME, message = "Last Name format is invalid.")
	private String lastName;

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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}