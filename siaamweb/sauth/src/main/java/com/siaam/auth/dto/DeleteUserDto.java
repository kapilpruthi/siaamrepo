package com.siaam.auth.dto;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.constraints.NotEmpty;

import com.siaam.auth.utils.CommonValidationUtils;;

/**
 * User Data Transfer Object for Deleting a user.
 * @author Kapil Pruthi
 */
public class DeleteUserDto {

	/**
	 * UniqueId supplied, either guid or onlineid of user.
	 */
	@NotEmpty(message = "Please pass either OnlineId or Guid as unique identifier.")
	private String uniqueId;

	/**
	 * id type, either "GUID" or "OID"
	 */
	@NotEmpty(message = "Please pass the input Type as GUID or OID.")
	private String idType;

	/**
	 * check if idtype and correponding uniqueid is supplied.
	 * @return true if valid input
	 */
	@AssertTrue(message = "Input format is incorrect")
	private boolean isValidInput() {
		if (idType.equals("OID")) {
			return (CommonValidationUtils.isValidUserName(uniqueId));
		} else if (idType.equals("GUID")) {
			return (CommonValidationUtils.isValidGuid(uniqueId));
		} else {
			return false;
		}
	}

	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	/**
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * @param idType the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

}