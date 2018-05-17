package com.siaam.auth.dto;

/**
 * User Data Transfer Object for User Details. Used to return non sensitive user
 * details to UI.
 * @author Kapil Pruthi
 */
/**
 * @author kapilpruthi
 *
 */
public class UserDetailsDto {

	/**
	 * First Name of the user.
	 */
	private String firstName;
	/**
	 * Last Name of the user.
	 */
	private String lastName;
	/**
	 * Email of the user.
	 */
	private String email;
	/**
	 * username of the user.
	 */
	private String userName;

	/**
	 * userId of the user, usually guid.
	 */
	private String userId;

	/**
	 * Userid Type of the user.
	 */
	private String userIdType;

	/**
	 * pwd expiry.
	 */
	private String pwdExpiryDate;
	/**
	 * last login date.
	 */
	private String lastLoginDate;

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
	 * @return the username
	 */

	/**
	 * @return the pwdExpiryDate
	 */
	public String getPwdExpiryDate() {
		return pwdExpiryDate;
	}

	/**
	 * @param pwdExpiryDate
	 *            the pwdExpiryDate to set
	 */
	public void setPwdExpiryDate(String pwdExpiryDate) {
		this.pwdExpiryDate = pwdExpiryDate;
	}

	/**
	 * @return the lastLoginDate
	 */
	public String getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * @param lastLoginDate
	 *            the lastLoginDate to set
	 */
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userIdType
	 */
	public String getUserIdType() {
		return userIdType;
	}

	/**
	 * @param userIdType
	 *            the userIdType to set
	 */
	public void setUserIdType(String userIdType) {
		this.userIdType = userIdType;
	}

}