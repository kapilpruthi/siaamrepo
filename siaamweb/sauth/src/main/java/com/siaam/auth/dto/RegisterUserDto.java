package com.siaam.auth.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.siaam.auth.utils.SiaamWebConstants;;

/**
 * User Data Transfer Object for Registration.
 * Serves as input form for Registration Service.
 * @author Kapil Pruthi
 */
public class RegisterUserDto {

	/**
	 * First name should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Please enter your First Name.")
	@Pattern(regexp = SiaamWebConstants.REGEX_FIRST_NAME, message = "firstname format is invalid.")
	private String firstname;

	/**
	 * Last Name should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Please enter your Last name.")
	@Pattern(regexp = SiaamWebConstants.REGEX_LAST_NAME, message = "lastname format is invalid.")
	private String lastname;

	/**
	 * Email should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Please enter your Email.")
	@Email(message = "Email format is invalid.")
	private String email;

	/**
	 * Username should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Please enter your username.")
	@Pattern(regexp = SiaamWebConstants.REGEX_OID, message = "Username format is invalid.")
	private String username;

	/**
	 * Password should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Please enter your password.")
	@Pattern(regexp = SiaamWebConstants.REGEX_PWD, message = "Password format is invalid.")
	private String password;

	/**
	 * Password should not be empty and follow below pasted regex.
	 */
	@NotEmpty(message = "Please confirm your password.")
	@Pattern(regexp = SiaamWebConstants.REGEX_PWD, message = "Confirm Password format is invalid.")
	private String confirmPassword;

	/**
	 * @return true if password and confirm password are same
	 */
	@AssertTrue(message = "password and confirm pwd are NOT same")
	private boolean isValidInput() {
		return (password.equals(confirmPassword) ? true : false);
	}
	
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param inFirstname the firstname to set
	 */
	public void setFirstname(String inFirstname) {
		this.firstname = inFirstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param inLastname the lastname to set
	 */
	public void setLastname(String inLastname) {
		this.lastname = inLastname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param inEmail the email to set
	 */
	public void setEmail(String inEmail) {
		this.email = inEmail;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param inUsername
	 *            the username to set
	 */
	public void setUsername(String inUsername) {
		this.username = inUsername;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param inPassword
	 *            the password to set
	 */
	public void setPassword(String inPassword) {
		this.password = inPassword;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param inConfirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String inConfirmPassword) {
		this.confirmPassword = inConfirmPassword;
	}

}